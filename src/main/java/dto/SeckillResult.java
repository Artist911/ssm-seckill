package dto;

/**
 * Bug free mode
 *
 * @author Dlz 小戴
 * @date 2022/07/27/15:23
 * @description 所有ajax的请求返回类型，封装json结果
 */
public class SeckillResult<T> {
    private boolean success;

    private T data;

    private String error;
    public SeckillResult(boolean status, T data) {
        this.success = status;
        this.data = data;
    }

    public SeckillResult(boolean status, String error) {
        this.success = status;
        this.error = error;
    }

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
