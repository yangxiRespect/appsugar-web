package org.appsugar.data.redis;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.repository.support.SimpleKeyValueRepository;
import org.springframework.data.repository.core.EntityInformation;

/**
 * redis数据访问接口增强实现类
 * @author NewYoung
 * 2016年11月23日下午4:58:29
 */
public class RedisIdEntityRepositoryImpl<T extends Serializable, C extends StringIdEntityCondition>
		extends SimpleKeyValueRepository<T, String>
		implements RedisIdEntityRepository<T, C>, RepositoryExtensionable<String, T, C> {

	protected RepositoryExtension<String, T, C> repositoryExtension;

	public RedisIdEntityRepositoryImpl(EntityInformation<T, String> metadata, KeyValueOperations operations) {
		super(metadata, operations);
	}

	@Override
	public void setRepositoryExtension(RepositoryExtension<String, T, C> repositoryExtension) {
		this.repositoryExtension = repositoryExtension;
	}

	@Override
	public RepositoryExtension<String, T, C> getRepositoryExtension() {
		return repositoryExtension;
	}

	@Override
	public <S extends T> S save(S entity) {
		if (entity instanceof GenericIdEntity) {
			GenericIdEntity<?> e = GenericIdEntity.class.cast(entity);
			Date date = new Date();
			if (Objects.isNull(e.identification())) {
				e.setCreatedAt(date);
			}
			e.setUpdatedAt(date);
		}
		return super.save(entity);
	}
}
