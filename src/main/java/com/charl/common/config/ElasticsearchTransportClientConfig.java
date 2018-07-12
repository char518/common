package com.charl.common.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;


@Configuration
public class ElasticsearchTransportClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchTransportClientConfig.class);

    @Value(value = "${elasticsearch.host}")
    private String esHost;

    @Value(value = "${elasticsearch.port}")
    private Integer esPort;

    @Value(value = "${elasticsearch.pool}")
    private Integer poolSize;

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        LOGGER.info("ES TransportClient 初始化开始...");

        TransportClient transportClient = null;

        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", poolSize.intValue())//增加线程池个数，暂时设为5
                    .build();

            transportClient = new PreBuiltTransportClient(settings);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esHost), esPort);

            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
            LOGGER.error("elasticsearch TransportClient create error:{}",e);
        }

        return transportClient;
    }

}
