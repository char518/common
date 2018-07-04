package com.charl.common.concurrent.akka;

import java.io.Serializable;

/**
 * @program: common
 * @description: 打招呼的人
 * @author: charl
 * @create: 2018-07-04 16:46
 **/
public class WhoToGreet implements Serializable {

    public final String greeterName;

    public WhoToGreet(String greeterName) {
        this.greeterName = greeterName;
    }
}
