package org.appsugar.data.redis;

import java.net.InetSocketAddress;

import org.appsugar.data.elasticsearch.SimpleElasticSearchIdEntityRepository;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(repositoryBaseClass = SimpleElasticSearchIdEntityRepository.class)
public class ApplicationConfiguration {
	@Bean
	public Client client() {
		TransportClient client = TransportClient.builder().build();
		TransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("localhost", 9350));
		client.addTransportAddress(address);
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}
}
