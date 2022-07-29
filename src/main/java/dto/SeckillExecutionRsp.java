package dto;

import entity.SuccessKilled;
import enums.SeckillStatEnum;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/27/15:16
 * @description 执行秒杀操作 封装结果
 */
public class SeckillExecutionRsp {
    private long seckill;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;

    public SeckillExecutionRsp(long seckill, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckill = seckill;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecutionRsp(long seckill, SeckillStatEnum statEnum) {
        this.seckill = seckill;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecutionRsp{" +
                "seckill=" + seckill +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }

    public long getSeckill() {
        return seckill;
    }

    public void setSeckill(long seckill) {
        this.seckill = seckill;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
