package dao;

import entity.SuccessKilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/27/10:24
 * @description 写入添加成功的记录 和查询
 */
public interface SucceesskilledDao {

    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userphone);

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
