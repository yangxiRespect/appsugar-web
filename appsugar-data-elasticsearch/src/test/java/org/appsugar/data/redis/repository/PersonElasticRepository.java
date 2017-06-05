package org.appsugar.data.redis.repository;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.data.elasticsearch.ElasticSearchIdEntityRepository;

/**
 * 人数据访问接口
 * @author NewYoung
 * 2016年10月31日下午5:56:23
 */
public interface PersonElasticRepository
		extends ElasticSearchIdEntityRepository<Person, String, StringIdEntityCondition> {
	/**
	 * 根据名称查询person
	 * @param name 名称
	 * @return 返回对应名称的person对象
	 */
	public Person findByName(String name);
}
