package org.appsugar.data.jpa.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
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
public class JpaIdEntityRepositoryImpl<T extends Serializable, C extends LongIdEntityCondition> extends
		QueryDslJpaRepository<T, Long> implements JpaIdEntityRepository<T, C>, RepositoryExtensionable<Long, T, C> {

	protected RepositoryExtension<Long, T, C> repositoryExtension;

	public JpaIdEntityRepositoryImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager) {
		this(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
	}

	public JpaIdEntityRepositoryImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager,
			EntityPathResolver resolver) {
		super(entityInformation, entityManager, resolver);
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
	public void setRepositoryExtension(RepositoryExtension<Long, T, C> repositoryExtension) {
		this.repositoryExtension = repositoryExtension;
	}

}
