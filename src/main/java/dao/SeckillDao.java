package dao;

import entity.Seckill;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/27/10:03
 * @description 自己做当前的测试时 错误许久 具体搜索 测试类自动注入为null
 * 注意，测试类调用的类中如果包含自动注入的对象，那么该类生成的对象必须使用自动注入，
 * 不能够new出来，否则该类中的自动注入对象会报空指针异常。
 * spring自动注入 经过配置文件 直接使用测试类  扫描不出来
 */
public interface SeckillDao {

    /**
     * 减库存
     *
     * @param seckillId 秒杀商品id
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
