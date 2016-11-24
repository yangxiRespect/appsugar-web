package org.appsugar.data.common.repository.ext;

import java.io.Serializable;
import java.util.List;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.repository.querydsl.QueryDslSpecification;
import org.appsugar.data.common.util.PageUtils;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.google.common.collect.Lists;
import com.querydsl.core.types.EntityPath;

/**
 * 数据访问接口扩展querydsl实现
 * @author NewYoung
 * 2016年11月23日下午1:56:29
 * @param <T> 实体类型
 * @param <C> 实体条件类型
 */
public class RepositoryExtensionQueryDslImpl<ID extends Serializable, T extends GenericIdEntity<ID>, C extends GenericIdEntityCondition<ID>>
		implements RepositoryExtension<ID, T, C> {

	private QueryDslPredicateExecutor<T> repository;

	private QueryDslSpecification<C, EntityPath<T>> specification;

	private EntityPath<T> entityPath;

	public RepositoryExtensionQueryDslImpl(QueryDslPredicateExecutor<T> repository,
			QueryDslSpecification<C, EntityPath<T>> specification, Class<T> entityClass) {
		super();
		this.repository = repository;
		this.specification = specification;
		this.entityPath = SimpleEntityPathResolver.INSTANCE.createPath(entityClass);
	}

	@Override
	public Page<T> findPageByCondition(C condition, Pageable pageable) {
		return PageUtils.toPage(
				repository.findAll(specification.toPredicate(condition, entityPath), PageUtils.toPageable(pageable)),
				pageable);
	}

	@Override
	public List<T> findByCondition(C condition) {
		return iterableToList(repository.findAll(specification.toPredicate(condition, entityPath)));
	}

	@Override
	public List<T> findByCondition(C condition, Sort sort) {
		return iterableToList(
				repository.findAll(specification.toPredicate(condition, entityPath), PageUtils.toSort(sort)));
	}

	@Override
	public long count(C condition) {
		return repository.count(specification.toPredicate(condition, entityPath));
	}

	protected List<T> iterableToList(Iterable<T> it) {
		if (it instanceof List) {
			return (List<T>) it;
		}
		return Lists.newArrayList(it);
	}

	protected QueryDslPredicateExecutor<T> getRepository() {
		return repository;
	}

	protected QueryDslSpecification<C, EntityPath<T>> getSpecification() {
		return specification;
	}

}
