package com.charl.common.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-03 15:23
 **/
@EnableCaching
@Configuration
/**
 * 只有classpath下有配置文件的时候才去加载
 */
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Bean(name = "redisTemplate")
    @SuppressWarnings("unchecked")
    @ConditionalOnMissingBean(name = "redisTemplate")
    RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //设置redis key的解析
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    /**
     * redis 缓存管理器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder =
                        RedisCacheManager.
                        RedisCacheManagerBuilder
                        .fromConnectionFactory(redisConnectionFactory);

        return redisCacheManagerBuilder.build();
    }


}
