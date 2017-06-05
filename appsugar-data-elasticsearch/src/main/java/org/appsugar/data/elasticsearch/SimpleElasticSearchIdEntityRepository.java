package org.appsugar.data.elasticsearch;

import java.io.Serializable;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

public class SimpleElasticSearchIdEntityRepository<T extends Serializable> extends SimpleElasticsearchRepository<T>
		implements ElasticSearchIdEntityRepository<T, String, StringIdEntityCondition>,
		RepositoryExtensionable<String, T, StringIdEntityCondition> {

	public SimpleElasticSearchIdEntityRepository() {
		super();
	}

	public SimpleElasticSearchIdEntityRepository(ElasticsearchEntityInformation<T, String> metadata,
			ElasticsearchOperations elasticsearchOperations) {
		super(metadata, elasticsearchOperations);
	}

	public SimpleElasticSearchIdEntityRepository(ElasticsearchOperations elasticsearchOperations) {
		super(elasticsearchOperations);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<String, T, StringIdEntityCondition> repositoryExtension) {

	}

	@Override
	public RepositoryExtension<String, T, StringIdEntityCondition> getRepositoryExtension() {
		return null;
	}

}
