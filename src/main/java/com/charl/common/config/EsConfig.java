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
 * @create: 2018-07-11 14:06
 **/
@Configuration
public class EsConfig {

    @Value("${elasticsearch.host}")
    private String hostName;

    @Value("${elasticsearch.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostName, port, "http"),
                        new HttpHost(hostName, 9201, "http")));
        return client;
    }

    @PreDestroy
    public void destroy(RestHighLevelClient restHighLevelClient) throws IOException {
        restHighLevelClient.close();
    }

}
