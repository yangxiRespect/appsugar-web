package org.appsugar.data.solr;

import java.io.Serializable;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Solr Repository 基础接口
 * @author NewYoung
 * 2016年11月7日下午1:41:53
 */
@NoRepositoryBean
public interface SolrIdEntityRepository<T extends Serializable, C extends StringIdEntityCondition>
		extends GenericIdEntityRepository<String, T, C>, SolrCrudRepository<T, String> {

}
