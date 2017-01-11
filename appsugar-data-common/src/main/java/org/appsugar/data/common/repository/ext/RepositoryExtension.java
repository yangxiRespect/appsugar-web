package org.appsugar.data.common.repository.ext;

import java.io.Serializable;
import java.util.List;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;

/**
 * 数据访问接口扩展
 * @author NewYoung
 * 2016年11月22日下午5:48:46
 * @param <ID> 实体ID类型
 * @param <T>  实体类型
 * @param <C>  实体条件类型
 */
public interface RepositoryExtension<ID extends Serializable, T, C extends GenericIdEntityCondition<ID>> {
	/** 
	 * 根据条件分页查询
	 */
	Page<T> findPageByCondition(C condition, Pageable pageable);

	/**
	 * 根据条件查询所以
	 */
	List<T> findByCondition(C condition);

	/**
	 * 根据条件查询并排序
	 */
	List<T> findByCondition(C condition, Sort sort);

	/**
	 * 查询符合条件的数目
	 * @author NewYoung
	 * 2016年11月15日下午6:09:10
	 */
	long count(C condition);
}
