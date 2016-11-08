package org.appsugar.data.redis.repository;

import org.appsugar.data.redis.BaseRedisTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author NewYoung
 * 2016年10月31日下午6:07:52
 */
public class PersonRedisRepositoryTest extends BaseRedisTest {

	@Autowired
	private PersonRedisRepository repository;

	@Test
	public void testFindById() {
		String id = "-1";
		Person result = repository.findOne(id);
		logger.debug("testFindById id {} result {}", id, result);
	}

}
