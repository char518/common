package com.charl.common.domin;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-20 10:54
 **/
public class BaseResponse<T> {

    private String code;

    private String msg;

    private T data;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse buildSuccessResponse() {
        return new BaseResponse("1", "SUCCESS", null);
    }

    public static <T> BaseResponse<T> buildSuccessResponse(T data) {
        return new BaseResponse("1", "SUCCESS", data);
    }

    public static BaseResponse buildFailedResponse(String msg) {
        return new BaseResponse("2", msg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
