package org.appsugar.data.solr.repository;

import org.appsugar.data.solr.SolrIdEntityRepository;
import org.appsugar.data.solr.entity.Person;

/**
 *用户solr数据访问接口
 * @author NewYoung
 * 2016年11月7日下午2:23:40
 */
public interface PersonSolrRepository extends SolrIdEntityRepository<Person> {

	/**
	 * 根据名称查询用户
	 * @param name 用户名称
	 * @return 返回对应名称的用户对象 or null
	 */
	public Person findByName(String name);
}
