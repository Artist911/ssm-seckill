package dto;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/27/15:08
 * @description 暴露秒杀地址DTO，出参类
 */

public class ExposerRsp {
    /**
     * exposed 是否暴露，是否开去秒杀
     * MD5 加密
     * now 当前时间
     */

    private boolean exposed;
    //通过时间去判断秒杀是否开启 并返回exposed 提供下一步的提示

    private String md5;

    private long seckillId;

    private long now;

    private long start;

    private long end;

    /**
     * 是否开启 md5 ，seckillId
     * @param exposed
     * @param md5
     * @param seckillId
     */
    public ExposerRsp(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public ExposerRsp(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public ExposerRsp(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "ExposerRsp{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
