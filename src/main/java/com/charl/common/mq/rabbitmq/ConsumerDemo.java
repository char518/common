package com.charl.common.mq.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-27 14:34
 **/
public class ConsumerDemo {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        //建立连接
        Connection connection = factory.newConnection();

        //创建信道
        Channel channel = connection.createChannel();

        //声明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);
        //声明队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定队列
        channel.queueBind(queueName,exchangeName,"hello-key");

        while (true) {
            boolean autoAck = false;

            String consumerTag = "";

            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();

                    String contentType = properties.getContentType();

                    System.out.println("消费的路由键：".concat(routingKey));

                    System.out.println("消费内容类型：" + (contentType));

                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息：");
                    String message = new String(body, "UTF-8");
                    System.out.println(message);
                }
            });
        }

    }

}
