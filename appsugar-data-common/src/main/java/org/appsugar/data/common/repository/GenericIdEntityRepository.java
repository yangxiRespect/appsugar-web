package org.appsugar.data.common.repository;

import java.io.Serializable;

import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.util.PageUtils;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericIdEntityRepository<ID extends Serializable, T extends GenericIdEntity<ID>>
		extends PagingAndSortingRepository<T, ID> {
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

}
