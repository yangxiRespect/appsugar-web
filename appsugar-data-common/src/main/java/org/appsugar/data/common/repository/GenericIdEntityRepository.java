package org.appsugar.data.common.repository;

import java.io.Serializable;

import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.util.PageUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface GenericIdEntityRepository<ID extends Serializable, T extends GenericIdEntity<ID>>
		extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	default Iterable<T> findAll(Sort sort) {
		return findAll(PageUtils.toSort(sort));
	}

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	default Page<T> findAll(Pageable pageable) {
		return PageUtils.toPage(findAll(PageUtils.toPageable(pageable)), pageable);
	}

	/**
	 * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
	 * found an empty {@link Iterable} is returned.
	 *
	 * @param example can be {@literal null}.
	 * @param sort the {@link Sort} specification to sort the results by, must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 * @since 1.10
	 */
	default <S extends T> Iterable<S> findAll(Example<S> example, Sort sort) {
		return findAll(example, PageUtils.toSort(sort));
	}

	/**
	 * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
	 * {@link Page} is returned.
	 *
	 * @param example can be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @return a {@link Page} of entities matching the given {@link Example}.
	 */
	default <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return PageUtils.toPage(findAll(example, PageUtils.toPageable(pageable)), pageable);
	}
}
