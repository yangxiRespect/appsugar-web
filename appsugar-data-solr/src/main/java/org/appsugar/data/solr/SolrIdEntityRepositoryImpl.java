package org.appsugar.data.solr;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.support.MappingSolrEntityInformation;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;

public class SolrIdEntityRepositoryImpl<T extends Serializable, C extends StringIdEntityCondition> extends
		SimpleSolrRepository<T, String> implements RepositoryExtensionable<String, T, C>, SolrIdEntityRepository<T, C> {

	public SolrIdEntityRepositoryImpl() {

	}

	public SolrIdEntityRepositoryImpl(MappingSolrEntityInformation<T, String> metadata, SolrTemplate solrOperations) {
		super(metadata, solrOperations);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<String, T, C> repositoryExtension) {

	}

	@Override
	public RepositoryExtension<String, T, C> getRepositoryExtension() {
		return null;
	}

	@Override
	public <S extends T> S save(S entity) {
		if (entity instanceof GenericIdEntity) {
			GenericIdEntity<?> e = GenericIdEntity.class.cast(entity);
			Date date = new Date();
			if (Objects.isNull(e.identification())) {
				e.setCreatedAt(date);
			}
			e.setUpdatedAt(date);
		}
		return super.save(entity);
	}
}
