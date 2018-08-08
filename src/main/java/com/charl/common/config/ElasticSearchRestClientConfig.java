package com.charl.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-03 11:44
 **/
@Configuration
public class ElasticSearchRestClientConfig {

    @Value(value = "${elasticsearch.host}")
    private String esHost;

    @Value(value = "${elasticsearch.http.port}")
    private Integer esPort;

    RestHighLevelClient restHighLevelClient = null;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(esHost, esPort, "http"),
                        new HttpHost(esHost, esPort + 1, "http"))
        );
        return restHighLevelClient;
    }

    @PreDestroy
    public void release() {
        if (null == restHighLevelClient) {
            return;
        }
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
