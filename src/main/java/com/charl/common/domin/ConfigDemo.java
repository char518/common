package com.charl.common.domin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-05 14:34
 **/
@Data
@ConfigurationProperties(prefix = "charl.demo")
public class ConfigDemo {

    private String userName;

    private String password;

    private String url;

    private int port;

}
