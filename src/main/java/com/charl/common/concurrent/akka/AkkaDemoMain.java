package com.charl.common.concurrent.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-04 17:17
 **/
public class AkkaDemoMain {

    public static void main(String[] args) throws TimeoutException {
        final ActorSystem system = ActorSystem.create("helloakka");

        //创建一个到greeter Actor的引用(管道)
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        //创建邮箱
        Inbox inbox = Inbox.create(system);
        //发送第一个消息（发送给greeter），消息类型是：WhoToGreet
        greeter.tell(new WhoToGreet("hello"),ActorRef.noSender());
        //发送第二个消息，邮箱发送消息
        inbox.send(greeter,new Greet());
        //接收消息返回
        Greeting greeting = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));

        System.out.println("greeting:"+greeting.message);

        //发送第三个消息
        greeter.tell(new WhoToGreet("nihao"),ActorRef.noSender());

        //发送第四个消息
        inbox.send(greeter,new Greet());
        //接收消息返回
        Greeting receive = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));

        System.out.println("receive:"+receive.message);

        //创建一个到：GreetPrinter 的引用
        ActorRef actorRef = system.actorOf(Props.create(GreetPrinter.class));
        //使用schedule 每一秒发送一个Greet消息给 greeterActor,然后把greeterActor的消息返回给greetPrinterActor
        system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), actorRef);

    }

}
