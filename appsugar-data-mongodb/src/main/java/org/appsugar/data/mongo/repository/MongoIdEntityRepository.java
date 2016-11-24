package org.appsugar.data.mongo.repository;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.appsugar.data.common.util.PageUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础repository提供自定义分页查询
 * @author NewYoung
 * 2016年9月21日下午6:32:32
 * @param <T>
 */
@NoRepositoryBean
public interface MongoIdEntityRepository<T extends StringIdEntity, C extends StringIdEntityCondition>
		extends MongoRepository<T, String>, GenericIdEntityRepository<String, T, C> {

	/**
	 * 兼容内部分页查询
	 */
	public default Page<T> findAll(Example<T> example, Pageable pageable) {
		return PageUtils.toPage(findAll(example, PageUtils.toPageable(pageable)), pageable);
	}

}
