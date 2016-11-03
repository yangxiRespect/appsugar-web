package org.appsugar.data.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * redisTemplate测试
 * @author NewYoung
 * 2016年10月31日下午2:44:35
 */
@ContextConfiguration(classes = ApplicationConfiguration.class)
public abstract class BaseRedisTest extends AbstractJUnit4SpringContextTests {
	@SuppressWarnings("hiding")
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
