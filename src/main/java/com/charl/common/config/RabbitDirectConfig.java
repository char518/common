package com.charl.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-27 16:58
 **/
@Component
public class RabbitDirectConfig {

    private static final String DIRECT_EXCHANGE = "COMMON_DIRECT_EXCHANGE";

    private static final String DIRECT_QUEUE = "COMMON_DIRECT_QUEUE";

    private static final String ROUT_KEY = "COMMON_ROUT_KEY";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    @Bean
    public Binding directQueueBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUT_KEY);
    }

    //发送MQ消息
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
