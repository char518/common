package com.charl.common.mq;

import com.charl.common.config.RabbitDirectConfig;
import com.charl.common.config.RabbitFanoutConfig;
import com.charl.common.config.RabbitTopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-28 09:38
 **/

@Component
public class SpringMqConsumer {

    @RabbitListener(queues = RabbitDirectConfig.DIRECT_QUEUE)
    public void directHandler(Object message) {
        System.out.println("接收到MQ消息：" + message);
    }

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_1)
    public void topicHandler1(Object message) {
        System.out.println("接收到topic_queue1队列的消息：" + message);
    }

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_2)
    public void topicHandler2(Object message) {
        System.out.println("接收到topic_queue2队列的消息：" + message);
    }

    @RabbitListener(queues = RabbitFanoutConfig.FANOUT_QUEUE_1)
    public void fanoutHandler1(Object message) {
        System.out.println("接收到fanout_queue1队列的消息：" + message);
    }

    @RabbitListener(queues = RabbitFanoutConfig.FANOUT_QUEUE_2)
    public void fanoutHandler2(Object message) {
        System.out.println("接收到fanout_queue2队列的消息：" + message);
    }

}
