package org.appsugar.data.common.repository.ext;

import java.io.Serializable;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;

/**
 * 数据访问可扩展接口
 * @author NewYoung
 * 2016年11月23日下午3:09:18
 */
public interface RepositoryExtensionable<ID extends Serializable, T extends GenericIdEntity<ID>, C extends GenericIdEntityCondition<ID>> {

	/**
	 * 设置扩展对象
	 * @author NewYoung
	 * 2016年11月23日下午3:15:20
	 */
	public void setRepositoryExtension(RepositoryExtension<ID, T, C> repositoryExtension);

}
