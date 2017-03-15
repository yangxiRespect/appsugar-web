package org.appsugar.data.jpa.repository.ext.sql.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	/**
	 * 每执行一次批处理后,调用该方法 
	 * @author NewYoung
	 * 2017年3月15日下午3:36:13
	 */
	public void onBatchExecuted(PreparedStatement stm, int[] results) throws SQLException;
}
