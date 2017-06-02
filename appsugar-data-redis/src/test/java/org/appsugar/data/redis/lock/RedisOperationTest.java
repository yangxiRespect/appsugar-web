package org.appsugar.data.redis.lock;

import java.util.concurrent.locks.Lock;

import org.appsugar.data.redis.BaseRedisTest;
import org.junit.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;

public class RedisOperationTest extends BaseRedisTest {

	/**
	 * Do not use this in production
	 */
	@Autowired
	private RedisLockRegistry registry;

	@Autowired
	private RedissonClient redissonClient;

	@Test
	public void testLock() {
		Lock lock = registry.obtain("t");
		lock.tryLock();
		logger.debug("redis locked");
		lock.unlock();
		logger.debug("redis unlocked");
	}

	@Test
	public void testRedissonLock() {
		Lock lock = redissonClient.getLock("anyLock");
		lock.tryLock();
		logger.debug("redisson  locked");
		lock.unlock();
		logger.debug("redisson unlocked");
	}
}
