package org.appsugar.data.redis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.redis.core.ListOperations;

/**
 * template 测试
 * @author NewYoung
 * 2016年10月31日下午3:08:37
 */
public class RedisTemplateTest extends BaseRedisTest {

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	@Test
	public void testListOperations() {
		String key = "NewYoung";
		listOps.leftPush(key, "I");
		listOps.rightPush(key, "love");
		listOps.rightPush(key, "you");
		int from = 0;
		int to = 3;
		List<String> result = listOps.range(key, 0, 3);
		logger.debug("testListOperations range key {} from {} to {} result is {}",
				new Object[] { key, from, to, result });
		listOps.remove(key, 0, listOps.size(key));
	}
}
