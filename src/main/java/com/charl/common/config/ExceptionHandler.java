package com.charl.common.config;

import com.charl.common.domin.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: common
 * @description: 异常处理类
 * @author: charl
 * @create: 2018-06-20 10:46
 **/
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse<Object> handleException(Exception e) {
        return BaseResponse.buildFailedResponse(e.getMessage().toString());
    }

}
