package org.appsugar.data.mongo.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
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
public class MongoIdEntityRepositoryImpl<T extends Serializable, C extends StringIdEntityCondition>
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
	public void setRepositoryExtension(RepositoryExtension<String, T, C> repositoryExtension) {
		this.repositoryExtension = repositoryExtension;
	}

	@Override
	public RepositoryExtension<String, T, C> getRepositoryExtension() {
		return repositoryExtension;
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
