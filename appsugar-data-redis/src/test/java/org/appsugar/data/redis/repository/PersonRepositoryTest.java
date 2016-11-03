package org.appsugar.data.redis.repository;

import org.appsugar.data.redis.BaseRedisTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @author NewYoung
 * 2016年10月31日下午6:07:52
 */
public class PersonRepositoryTest extends BaseRedisTest {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void testFindAll() {
		Iterable<Person> result = repository.findAll();
		logger.debug("testFindAll result is {}", result);
		//System.out.println(ctx.getBeansOfType(RedisRepository.class).get("personRepository").getClass());
	}

}
