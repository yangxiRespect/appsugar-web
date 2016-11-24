package org.appsugar.data.mongo.repository;

import java.util.List;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

/**
 * mongodb数据访问接口扩展类
 * @author NewYoung
 * 2016年11月22日下午6:37:59
 */
public class MongoIdEntityRepositoryImpl<T extends StringIdEntity, C extends StringIdEntityCondition>
		extends QueryDslMongoRepository<T, String>
		implements MongoIdEntityRepository<T, C>, RepositoryExtensionable<String, T, C> {

	protected RepositoryExtension<String, T, C> repositoryExtension;

	public MongoIdEntityRepositoryImpl(MongoEntityInformation<T, String> entityInformation,
			MongoOperations mongoOperations, EntityPathResolver resolver) {
		super(entityInformation, mongoOperations, resolver);
	}

	public MongoIdEntityRepositoryImpl(MongoEntityInformation<T, String> entityInformation,
			MongoOperations mongoOperations) {
		this(entityInformation, mongoOperations, SimpleEntityPathResolver.INSTANCE);
	}

	@Override
	public Page<T> findPageByCondition(C condition, Pageable pageable) {
		return repositoryExtension.findPageByCondition(condition, pageable);
	}

	@Override
	public List<T> findByCondition(C condition) {
		return repositoryExtension.findByCondition(condition);
	}

	@Override
	public List<T> findByCondition(C condition, Sort sort) {
		return repositoryExtension.findByCondition(condition, sort);
	}

	@Override
	public long count(C condition) {
		return repositoryExtension.count(condition);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<String, T, C> repositoryExtension) {
		this.repositoryExtension = repositoryExtension;
	}

}
