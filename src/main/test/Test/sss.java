import dao.SeckillDao;
import dto.ExposerRsp;
import dto.SeckillExecutionRsp;
import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SecKillService;

import java.util.List;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/28/21:30
 * @description
 * 配置spring和junit的整合，junit整合加载spingIOC容器
 * spring-test,junit
 * Spring的容器环境是啥呢？
 * 比如常见的 Service  Dao  Action ， 这些个东西，都在Spring容器里，junit需要将他们拿到，并且使用来测试。
 */
//这种写法是为了让测试在Spring容器环境下执行。
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit，spring配置文件
@ContextConfiguration({"classpath:spring/*.xml"})
public class sss {

    @Autowired
    private SecKillService service;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void queryAll() throws Exception {
        //Java虚拟机不保存形参
        //queryAll(int offset,int limit) -> queryAll(arg0,arg1)
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
    @Test
    public  void queryById(){
        Seckill byId = service.getById(1000);
        System.out.println(byId);
    }
    @Test
    public  void demo000(){
        ExposerRsp exposerRsp = service.exproySeckillUrl(1001);
        System.out.println(exposerRsp);
    }

    @Test
    public  void demo111(){
        SeckillExecutionRsp seckillExecutionRsp = service.executeSeckill(1001, 111, "9f26737a177b65c934341b48e4cdf21c");
        System.out.println(seckillExecutionRsp);
    }
}
