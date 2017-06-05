package org.appsugar.data.common.repository.ext;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;

/**
 * 数据访问可扩展接口
 * @author NewYoung
 * 2016年11月23日下午3:09:18
 */
public interface RepositoryExtensionable<ID extends Serializable, T, C extends GenericIdEntityCondition<ID>>
		extends RepositoryExtension<ID, T, C> {

	/**
	 * 设置扩展对象
	 * @author NewYoung
	 * 2016年11月23日下午3:15:20
	 */
	void setRepositoryExtension(RepositoryExtension<ID, T, C> repositoryExtension);

	/**
	 * 获取扩展对象
	 * @author NewYoung
	 * 2017年6月5日下午3:56:33
	 */
	RepositoryExtension<ID, T, C> getRepositoryExtension();

	/** 
	 * 根据条件分页查询
	 */
	@Override
	default Page<T> findPageByCondition(C condition, Pageable pageable) {
		return checkAndGetExtension().findPageByCondition(condition, pageable);
	}

	/**
	 * 根据条件查询所以
	 */
	@Override
	default List<T> findByCondition(C condition) {
		return checkAndGetExtension().findByCondition(condition);
	}

	/**
	 * 根据条件查询并排序
	 */
	@Override
	default List<T> findByCondition(C condition, Sort sort) {
		return checkAndGetExtension().findByCondition(condition, sort);
	}

	/**
	 * 查询符合条件的数目
	 * @author NewYoung
	 * 2016年11月15日下午6:09:10
	 */
	@Override
	default long count(C condition) {
		return checkAndGetExtension().count(condition);
	}

	/**
	 * 检查并获取扩展
	 * @author NewYoung
	 * 2017年6月5日下午4:27:09
	 */
	default RepositoryExtension<ID, T, C> checkAndGetExtension() {
		RepositoryExtension<ID, T, C> result = getRepositoryExtension();
		Objects.requireNonNull(result, "repository does  not support  querydsl");
		return result;
	}

}
