package org.appsugar.data.elasticsearch;

import java.io.Serializable;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.NumberKeyedRepository;

public class NumberKeyedElasticSearchIdEntityRepository<T extends Serializable> extends NumberKeyedRepository<T, Long>
		implements ElasticSearchIdEntityRepository<T, Long, LongIdEntityCondition>,
		RepositoryExtensionable<Long, T, LongIdEntityCondition> {

	public NumberKeyedElasticSearchIdEntityRepository() {
		super();
	}

	public NumberKeyedElasticSearchIdEntityRepository(ElasticsearchEntityInformation<T, Long> metadata,
			ElasticsearchOperations elasticsearchOperations) {
		super(metadata, elasticsearchOperations);
	}

	public NumberKeyedElasticSearchIdEntityRepository(ElasticsearchOperations elasticsearchOperations) {
		super(elasticsearchOperations);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<Long, T, LongIdEntityCondition> repositoryExtension) {

	}

	@Override
	public RepositoryExtension<Long, T, LongIdEntityCondition> getRepositoryExtension() {
		return null;
	}

}
