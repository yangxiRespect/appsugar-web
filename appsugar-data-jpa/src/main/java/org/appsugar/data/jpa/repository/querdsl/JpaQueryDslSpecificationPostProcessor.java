package org.appsugar.data.jpa.repository.querdsl;

import java.util.List;

import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.appsugar.data.common.repository.querydsl.QueryDslSpecification;
import org.appsugar.data.common.repository.querydsl.QuerydslSpecificationRepositoryPostProcessor;
import org.appsugar.data.jpa.repository.JpaGenericIdEntityRepository;

import com.google.common.collect.Lists;

/**
 * querydsl 
 * @author NewYoung
 * 2016年11月24日下午12:19:38
 */
@SuppressWarnings({ "rawtypes" })
public class JpaQueryDslSpecificationPostProcessor extends QuerydslSpecificationRepositoryPostProcessor {
	/**
	 * 获取按顺序匹配符合条件的Specification class
	 * @author NewYoung
	 * 2016年11月24日上午11:07:48
	 */

	@Override
	protected List<Class<? extends QueryDslSpecification>> getOrderedSatisfySpecificationClass() {
		return Lists.newArrayList(JpaGenericQueryDslSpecification.class, QueryDslSpecification.class);
	}

	/**
	 * 获取需要增强的基类接口
	 * @author NewYoung
	 * 2016年11月24日上午11:03:21
	 */
	@Override
	protected Class<? extends GenericIdEntityRepository> getBaseRepositoryClass() {
		return JpaGenericIdEntityRepository.class;
	}

}
