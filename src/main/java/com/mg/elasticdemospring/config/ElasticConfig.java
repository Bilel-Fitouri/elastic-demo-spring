package com.mg.elasticdemospring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@AllArgsConstructor
public class ElasticConfig
{
    private final AdditionalClientSettings additionalClientSettings;

    @Bean
    public Client client() throws Exception {
        Settings settings = Settings.builder()
           .put("cluster.name", additionalClientSettings.user)
           .put("client.transport.ping_timeout", additionalClientSettings.transport.pingTimeout, TimeUnit.SECONDS)
           .put("client.transport.nodes_sampler_interval", additionalClientSettings.transport.nodeSamplerInterval, TimeUnit.SECONDS)
           .build();

        return new PreBuiltTransportClient(settings)
           .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(additionalClientSettings.ip), additionalClientSettings.port));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

    @ConfigurationProperties(prefix="elasticsearch")
    @Data
    @Accessors(chain = true)
    public static class AdditionalClientSettings {

         Index index;
         String ip;
         int port;
         String cluster;
         String user;
         String password;
         Query query;
         Transport transport;

        @Data
        public static class Query{
             String name;
             Limit limit;

            @Data
            public static class Limit{
                 int max;
            }
        }

        @Data
        public static class Index{
             String name;
        }

        @Data
        public static class Transport{
             Long pingTimeout = 5L;
             Long nodeSamplerInterval = 5L;
        }
    }
}
