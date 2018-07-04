package com.charl.common.concurrent.akka;

import java.io.Serializable;

/**
 * @program: common
 * @description: 打招呼这个动作
 * @author: charl
 * @create: 2018-07-04 16:45
 **/
public class Greeting implements Serializable {

    public final String message;

    public Greeting(String message) {
        this.message = message;
    }
}
