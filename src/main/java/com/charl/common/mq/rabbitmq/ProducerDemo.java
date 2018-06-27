package com.charl.common.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-27 14:34
 **/
public class ProducerDemo {

    public static void main(String[] arg) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        //创建到Mq的连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();

        String exchangeName = "hello-exchange";
        //声明交换器
        channel.exchangeDeclare(exchangeName, "direct", true);

        byte[] bytes = "hello there".getBytes();

        //发布消息
        channel.basicPublish(exchangeName, "hello-key", null, bytes);
        System.out.println("发布成功");

        channel.close();

        connection.close();
    }

}
