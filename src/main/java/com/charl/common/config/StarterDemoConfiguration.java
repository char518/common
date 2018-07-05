package com.charl.common.config;

import com.charl.common.domin.ConfigDemo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-05 14:33
 **/

@Configuration
@EnableConfigurationProperties({ConfigDemo.class})
@ConditionalOnProperty(prefix = "charl.demo", value = "enabled", matchIfMissing = true)
public class StarterDemoConfiguration {

    @Bean
    public ConfigDemo configDemo() {
        return new ConfigDemo();
    }

}
