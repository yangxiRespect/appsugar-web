package org.appsugar.data.elasticsearch;

import java.io.Serializable;
import java.util.UUID;

import org.appsugar.bean.condition.UUIDEntityCondition;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.UUIDElasticsearchRepository;

public class UUIDElasticSearchIdEntityRepository<T extends Serializable> extends UUIDElasticsearchRepository<T>
		implements ElasticSearchIdEntityRepository<T, UUID, UUIDEntityCondition>,
		RepositoryExtensionable<UUID, T, UUIDEntityCondition> {

	public UUIDElasticSearchIdEntityRepository() {
		super();
	}

	public UUIDElasticSearchIdEntityRepository(ElasticsearchEntityInformation<T, UUID> metadata,
			ElasticsearchOperations elasticsearchOperations) {
		super(metadata, elasticsearchOperations);
	}

	public UUIDElasticSearchIdEntityRepository(ElasticsearchOperations elasticsearchOperations) {
		super(elasticsearchOperations);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<UUID, T, UUIDEntityCondition> repositoryExtension) {

	}

	@Override
	public RepositoryExtension<UUID, T, UUIDEntityCondition> getRepositoryExtension() {
		return null;
	}

}
