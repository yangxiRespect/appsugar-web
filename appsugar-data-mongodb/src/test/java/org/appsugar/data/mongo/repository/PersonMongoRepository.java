package org.appsugar.data.mongo.repository;

import java.util.List;

import org.appsugar.data.mongo.domain.PersonCondition;
import org.appsugar.data.mongo.entity.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author shenliuyang
 * @email  shenliuyang@gmail.com
 *
 * 2015年8月6日上午11:36:23
 */
@Repository
public interface PersonMongoRepository
		extends MongoIdEntityRepository<Person, PersonCondition>, PersonMongoRepositoryCustom {

	/**
	 * 根据城市模糊查询
	 * @param city 前驱匹配
	 */
	public List<Person> findByAddressCityStartingWith(String city);

	/**
	 * 根据person.id 查询对于address
	 * 
	 */
	@Query(value = "{'id':?0}", fields = "{'address':1}")
	public Person findAddressById(String id);

}
