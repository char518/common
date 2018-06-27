package com.charl.common.async;

/**
 * @program: product-gateway
 * @description: 回调封装
 * @author: charl
 * @create: 2018-06-19 16:40
 **/
public interface TypedTask<T> {

    T doAction() throws Exception;

    String type();

}
