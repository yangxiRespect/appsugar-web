package org.appsugar.data.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
@EnableRedisRepositories(repositoryBaseClass = RedisIdEntityRepositoryImpl.class)
public class ApplicationConfiguration {
	@Bean
	public RedisConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		return template;
	}

	@Bean
	public RedisLockRegistry lockRegistry() {
		return new RedisLockRegistry(connectionFactory(), "test");
	}

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1:6379");
		return Redisson.create(config);
	}
}
