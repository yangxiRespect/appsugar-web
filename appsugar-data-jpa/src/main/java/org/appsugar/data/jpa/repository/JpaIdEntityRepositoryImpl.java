package org.appsugar.data.jpa.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

/**
 * 针对idEntity bean做增强
 * @author NewYoung
 * 2016年2月25日下午2:33:11
 */
public class JpaIdEntityRepositoryImpl<T extends Serializable, ID extends Serializable, C extends GenericIdEntityCondition<ID>>
		extends QueryDslJpaRepository<T, ID>
		implements JpaGenericIdEntityRepository<T, ID, C>, RepositoryExtensionable<ID, T, C> {

	protected RepositoryExtension<ID, T, C> repositoryExtension;

	public JpaIdEntityRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		this(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
	}

	public JpaIdEntityRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager,
			EntityPathResolver resolver) {
		super(entityInformation, entityManager, resolver);
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

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		return super.save(entities);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<ID, T, C> repositoryExtension) {
		this.repositoryExtension = repositoryExtension;
	}

	@Override
	public RepositoryExtension<ID, T, C> getRepositoryExtension() {
		return repositoryExtension;
	}

}
