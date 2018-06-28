package com.charl.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: common
 * @description: direct模式会根据directKey 找到对应的队列
 * @author: charl
 * @create: 2018-06-27 16:58
 **/
@Configuration
public class RabbitDirectConfig {

    public static final String DIRECT_EXCHANGE = "COMMON_DIRECT_EXCHANGE";

    public static final String DIRECT_QUEUE = "COMMON_DIRECT_QUEUE";

    public static final String ROUT_KEY = "COMMON_ROUT_KEY";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE,true,false);
    }

    /**
     * 定义一个直接队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    /**
     * @return
     */
    @Bean
    public Binding building(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(ROUT_KEY);
    }

    /**
     * 发送消息
     *
     * @param factory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
