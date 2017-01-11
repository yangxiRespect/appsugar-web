package org.appsugar.data.redis;

import java.io.Serializable;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Redis Repository
 * @author NewYoung
 * 2016年10月31日下午2:35:02
 * @param <T>
 */
@NoRepositoryBean
public interface RedisIdEntityRepository<T extends Serializable, C extends StringIdEntityCondition>
		extends GenericIdEntityRepository<String, T, C> {

}
