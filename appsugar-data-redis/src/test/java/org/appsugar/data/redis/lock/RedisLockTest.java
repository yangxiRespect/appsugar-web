package org.appsugar.data.redis.lock;

import java.util.concurrent.locks.Lock;

import org.appsugar.data.redis.BaseRedisTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;

public class RedisLockTest extends BaseRedisTest {

	/**
	 * Do not use this in production
	 */
	@Autowired
	private RedisLockRegistry registry;

	@Test
	public void testLock() {
		Lock lock = registry.obtain("t");
		lock.tryLock();
		logger.debug("redis locked");
		lock.unlock();
		logger.debug("redis unlocked");
	}
}
