package org.appsugar.data.jpa.repository.ext.sql.batch;

import java.sql.SQLException;
import java.util.Collection;

/**
 * sql 批处理
 * @author NewYoung
 * 2016年2月24日下午5:56:31
 */
public interface BatchOperation<T> {
	public int executeBatch(Collection<T> entities) throws SQLException;
}
