/**
 * 解决querydsl 生成q class对象问题
 * @author NewYoung
 * 2016年11月23日下午1:59:34
 */
@QueryEntities({ org.appsugar.bean.entity.LongIdEntity.class, org.appsugar.bean.entity.GenericIdEntity.class,
		org.appsugar.bean.entity.StringIdEntity.class })
package org.appsugar.data.common.repository;

import com.querydsl.core.annotations.QueryEntities;
