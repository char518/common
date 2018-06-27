package com.charl.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-21 09:41
 **/
@ConfigurationProperties(prefix = "guava.cache.properties")
@Data
public class GuavaProperties {

    private long maximumSize;

    private long maximumWeight;

    private long expireAfterWriteDuration;

    private long expireAfterAccessDuration;

    private long refreshDuration;

    private int initialCapacity;

    private int concurrencyLevel;

}
