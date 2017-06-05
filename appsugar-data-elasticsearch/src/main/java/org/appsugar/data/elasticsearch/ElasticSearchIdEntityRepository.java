package org.appsugar.data.elasticsearch;

import java.io.Serializable;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base elasticsearch Repository
 * @author NewYoung
 * 2016年10月31日下午2:35:02
 * @param <T>
 */
@NoRepositoryBean
public interface ElasticSearchIdEntityRepository<T extends Serializable, ID extends Serializable, C extends GenericIdEntityCondition<ID>>
		extends GenericIdEntityRepository<ID, T, C>, ElasticsearchRepository<T, ID> {

}
