package org.appsugar.data.jpa.repository.ext.sql.batch;

import java.util.List;

/**
 * 批处理数据获取器
 * @author NewYoung
 * 2016年2月24日下午5:59:16
 * @param <T>
 */
public interface BatchOperationInformation<T> {

	/**
	 * sql语句
	 * @example delet from as_user where id = ?
	 * @example update as_user set name = ? , login_name = ? where id = ?
	 * @example insert  into as_user (id,name,login_name) value (?,?,?)
	 * @example insert  into as_user (id,name,login_name) values (?,?,?)
	 */
	public String sql();

	/**
	 * 获取对应参数
	 */
	public List<Object> getParameterList(T entity);

	/**
	 * 每次批操作数
	 */
	public int batchSize();
}
