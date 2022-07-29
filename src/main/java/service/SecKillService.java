package service;

import dto.ExposerRsp;
import dto.SeckillExecutionRsp;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;

import java.util.List;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/26/21:25
 * @description
 */
public interface SecKillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSecKillList();

    /**
     * 单个ID查询
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     *秒杀开始时 给出链接地址
     * 不然给出系统时间
     * @param seckillId
     * @return
     */
    ExposerRsp exproySeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecutionRsp  executeSeckill(long seckillId, long userPhone, String md5
    ) throws SeckillException, RepeatKillException, SeckillCloseException;


}
