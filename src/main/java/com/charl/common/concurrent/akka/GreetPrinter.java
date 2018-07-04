package com.charl.common.concurrent.akka;

import akka.actor.UntypedAbstractActor;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-04 17:16
 **/
public class GreetPrinter extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Greeting) {
            System.out.println(((Greeting) message).message);
        }
    }
}
