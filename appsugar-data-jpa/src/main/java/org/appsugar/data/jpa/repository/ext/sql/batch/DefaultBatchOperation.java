package org.appsugar.data.jpa.repository.ext.sql.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 默认批处理操作
 * @author NewYoung
 * 2016年2月24日下午5:58:06
 * @param <T>
 */
public class DefaultBatchOperation<T> implements BatchOperation<T> {

	protected BatchOperationInformation<T> information;

	protected Connection connection;

	public DefaultBatchOperation(BatchOperationInformation<T> information, Connection connection) {
		super();
		this.information = information;
		this.connection = connection;
	}

	@Override
	public int executeBatch(Collection<T> entities) throws SQLException {
		try (PreparedStatement stm = connection.prepareStatement(information.sql())) {
			int batchSize = information.batchSize();
			if (batchSize < 1) {
				throw new IllegalArgumentException("batchSize must greaterThan zero");
			}
			int i = 0;
			int result = 0;
			for (T entity : entities) {
				List<Object> parameterList = information.getParameterList(entity);
				setParameter(1, parameterList, stm);
				stm.addBatch();
				if (i++ % batchSize == 0) {
					result += sum(stm.executeBatch());
					stm.clearBatch();
				}
			}
			if (i % batchSize != 0) {
				result += sum(stm.executeBatch());
			}
			return result;
		}
	}

	protected void setParameter(int start, List<Object> parameterList, PreparedStatement stm) throws SQLException {
		int index = start;
		for (Object parameter : parameterList) {
			stm.setObject(index++, parameter);
		}
	}

	protected int sum(int[] values) {
		int result = 0;
		for (int value : values) {
			result += value;
		}
		return result;
	}
}
