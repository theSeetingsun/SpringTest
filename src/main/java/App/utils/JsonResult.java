package App.utils;

public class JsonResult {
    //是否操作成功 默认true
    private Boolean success = true;

    //提示信息
    private String message = "操作成功！";

    //响应到前端 携带的数据
    private Object data;

    private JsonResult(){}

    /**
     * 成功不带消息 使用默认消息
     * @return
     */
    public static JsonResult success(){
        return new JsonResult();
    }

    /**
     * 成功并携带数据
     * @param data
     * @return
     */
    public static JsonResult success(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 失败 并携带错误提示消息
     * @param message
     * @return
     */
    public static JsonResult error(String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
