package com.charl.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 14:17
 **/
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caffeineCaches = new ArrayList<>();
        caffeineCaches.add(new CaffeineCache("user", Caffeine.newBuilder().
                expireAfterWrite(5, TimeUnit.SECONDS).maximumSize(1024).build()));
        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }


}
