package org.appsugar.data.solr;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Solr Repository 基础接口
 * @author NewYoung
 * 2016年11月7日下午1:41:53
 */
public interface SolrIdEntityRepository<T extends StringIdEntity, C extends StringIdEntityCondition>
		extends GenericIdEntityRepository<String, T, C>, SolrCrudRepository<T, String> {

}
