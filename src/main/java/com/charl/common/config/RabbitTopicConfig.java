package com.charl.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-28 14:09
 **/
@Configuration
public class RabbitTopicConfig {

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String TOPIC_QUEUE_1 = "topic_queue1";

    public static final String TOPIC_QUEUE_2 = "topic_queue2";

    /**
     * topic模式:路由键必须是一串字符，用句号（.）隔开，（*）主要用于匹配路由键指定位置的一个单词， 井号（#）就表示相当于一个或者多个单词
     */
    public static final String TOPIC_ROUT_KEY_1 = "topic.key1";

    public static final String TOPIC_ROUT_KEY_2 = "topic.#";

    @Bean
    public Queue queue1(){
        return new Queue(TOPIC_QUEUE_1,true);
    }

    @Bean
    public Queue queue2(){
        return new Queue(TOPIC_QUEUE_2,true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with(TOPIC_ROUT_KEY_1);
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with(TOPIC_ROUT_KEY_2);
    }

}
