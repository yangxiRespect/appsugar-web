package org.appsugar.data.solr;

import java.util.List;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;

public class SolrIdEntityRepositoryImpl<T extends StringIdEntity, C extends StringIdEntityCondition> extends
		SimpleSolrRepository<T, String> implements RepositoryExtensionable<String, T, C>, SolrIdEntityRepository<T, C> {

	@Override
	public void setRepositoryExtension(RepositoryExtension<String, T, C> repositoryExtension) {

	}

	@Override
	public Page<T> findPageByCondition(C condition, Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findByCondition(C condition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findByCondition(C condition, Sort sort) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long count(C condition) {
		throw new UnsupportedOperationException();
	}

}
