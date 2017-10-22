package com.mg.elasticdemospring;

import com.mg.elasticdemospring.config.ElasticConfig;
import com.mg.elasticdemospring.repository.UnitBridgeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableConfigurationProperties({ ElasticConfig.AdditionalClientSettings.class})
@EnableElasticsearchRepositories(basePackageClasses = UnitBridgeRepository.class)
public class ElasticDemoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticDemoSpringApplication.class, args);
	}
}
