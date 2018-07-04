package com.charl.common.concurrent.akka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-04 16:48
 **/
public class Greeter extends UntypedActor {

    private String greeting;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof WhoToGreet) {
            greeting = "hello," + ((WhoToGreet) message).greeterName;
        } else if (message instanceof Greet) {
            getSender().tell(new Greeting(greeting), ActorRef.noSender());
        } else {
            unhandled(message);
        }
    }
}
