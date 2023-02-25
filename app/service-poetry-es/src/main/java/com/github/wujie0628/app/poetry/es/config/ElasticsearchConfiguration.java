package com.github.wujie0628.app.poetry.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/**
 * @date 2022/7/15
 **/
@Configuration
public class ElasticsearchConfiguration {
 
    @Value("${elasticsearch.host}")
    private String host;
 
    @Value("${elasticsearch.port}")
    private Integer port;
 
    @Value("${elasticsearch.connTimeout}")
    private Integer connTimeout;
 
    @Value("${elasticsearch.socketTimeout}")
    private Integer socketTimeout;
 
    @Value("${elasticsearch.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;


    @Bean
    public RestClient restClient() {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport elasticsearchTransport(RestClient restClient) {
        // 使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper()
        );
        return transport;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport elasticsearchTransport) {
        ElasticsearchClient client = new ElasticsearchClient(elasticsearchTransport);
        return client;
    }
}