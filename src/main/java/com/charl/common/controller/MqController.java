package com.charl.common.controller;

import com.charl.common.config.RabbitDirectConfig;
import com.charl.common.config.RabbitFanoutConfig;
import com.charl.common.config.RabbitTopicConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-28 10:03
 **/
@RestController
@Api(value = "MQ测试Controller")
@RequestMapping(value = "/mq")
public class MqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "direct模式测试")
    @GetMapping(value = "/sendDirect/{message}")
    public String sendDirectMessage(@PathVariable(value = "message") String message) {
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE, RabbitDirectConfig.ROUT_KEY, message);
        System.out.println("message:" + message);
        return message;
    }

    @ApiOperation(value = "topic模式测试")
    @GetMapping(value = "/sendTopic/{message}")
    public String sendTopicMessage(@PathVariable(value = "message") String message) {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_EXCHANGE, "topic.key2", message.concat("two!!"));
        return message;
    }

    @ApiOperation(value = "fanout模式测试")
    @GetMapping(value = "/sendFanout/{message}")
    public String sendFanoutMessage(@PathVariable(value = "message") String message) {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_EXCHANGE, "", message);
        return message;
    }

}
