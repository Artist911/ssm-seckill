package service.Imp;


import dao.SeckillDao;
import dao.SucceesskilledDao;
import dto.ExposerRsp;
import dto.SeckillExecutionRsp;
import entity.Seckill;
import entity.SuccessKilled;
import enums.SeckillStatEnum;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import service.SecKillService;


import java.util.Date;
import java.util.List;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/26/21:28
 * @description
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SucceesskilledDao succeesskilledDao;

    private final String salt = "sdaafdalk21dsaasmdl^&%^";

    @Override
    public List<Seckill> getSecKillList() {
        return seckillDao.queryAll(0, 10);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * md5加密
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    public ExposerRsp exproySeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            //系统没有该数据
            return new ExposerRsp(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        //jdbc配置文件必须加入时区，不然获取时间不准确
        //应用程序时区和系统时区不同导致的
        if (nowTime.before(startTime) || nowTime.after(endTime)) {
            //看时间 jsp页面显示未开始还是已结束
            return new ExposerRsp(false, seckillId, nowTime.getTime(),
                    startTime.getTime(), endTime.getTime());
        }
        //        转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new ExposerRsp(true, md5, seckillId);

    }

    /**
     * 使用注解控制事务方法的优点
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求 或者 剥离到事务方法之外
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制。
     */
    @Override
    @Transactional
    public SeckillExecutionRsp executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        //md5 被改写 系统不在通过
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();

        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录
                throw new SeckillException("seckill is closed");
            } else {
                int insertCount = succeesskilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {

                    //秒杀成功
                    SuccessKilled successKilled = succeesskilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecutionRsp(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (RepeatKillException | SeckillCloseException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
//            所有编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}

