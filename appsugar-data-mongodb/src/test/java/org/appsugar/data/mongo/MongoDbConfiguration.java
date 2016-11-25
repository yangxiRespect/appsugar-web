package org.appsugar.data.mongo;

import org.appsugar.data.mongo.repository.MongoIdEntityRepositoryImpl;
import org.appsugar.data.mongo.repository.querdsl.MongoQueryDslSpecificationPostProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryBaseClass = MongoIdEntityRepositoryImpl.class)
public class MongoDbConfiguration {

	@Bean
	public MongoQueryDslSpecificationPostProcess postProcess() {
		return new MongoQueryDslSpecificationPostProcess();
	}
}
