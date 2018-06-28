package com.charl.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-28 14:38
 **/
@Configuration
public class RabbitFanoutConfig {

    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";

    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";

    public static final String FANOUT_EXCHANGE = "FANOUT_EXCHANGE";

    /**
     * fanout模式下是直接分发到各个绑定的queue上，并不会根据route_key进行匹配
     */
    public static final String FANOUT_ROUTE_KEY = "FANOUT_ROUTE_KEY";

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

}
