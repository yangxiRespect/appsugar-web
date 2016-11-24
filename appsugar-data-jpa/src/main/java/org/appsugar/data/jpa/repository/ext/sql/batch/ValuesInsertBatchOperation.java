package org.appsugar.data.jpa.repository.ext.sql.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author NewYoung
 * 2016年2月24日下午6:25:07
 * @param <T>
 */
public class ValuesInsertBatchOperation<T> extends DefaultBatchOperation<T> {

	public ValuesInsertBatchOperation(BatchOperationInformation<T> information, Connection connection) {
		super(information, connection);
	}

	@Override
	public int executeBatch(Collection<T> entities) throws SQLException {
		int batchSize = information.batchSize();
		int totalQuantity = entities.size();
		int batchCount = totalQuantity / batchSize;
		String sql = information.sql();
		String valueString = sql.substring(sql.lastIndexOf("("), sql.lastIndexOf(")") + 1);
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = entities.iterator();
		int result = 0;
		for (int i = 0; i < batchCount; i++) {
			try (PreparedStatement stm = connection
					.prepareStatement(append(sb.append(sql), valueString, ",", batchSize - 1))) {
				int index = 1;
				for (int j = 0; j < batchSize; j++) {
					List<Object> parameterList = information.getParameterList(it.next());
					setParameter(index, parameterList, stm);
					index += parameterList.size();
				}
				result += stm.executeUpdate();
			}
			sb.delete(0, sb.length());
		}
		int remainCount = entities.size() % batchSize;
		if (remainCount < 1) {
			return result;
		}
		try (PreparedStatement stm = connection
				.prepareStatement(append(sb.append(sql), valueString, ",", remainCount - 1))) {
			int index = 1;
			for (; it.hasNext();) {
				List<Object> parameterList = information.getParameterList(it.next());
				setParameter(index, parameterList, stm);
				index += parameterList.size();
			}
			result += stm.executeUpdate();
		}
		return result;
	}

	protected String append(StringBuilder sb, String v, String symbol, int times) {
		for (int i = 0; i < times; i++) {
			sb.append(symbol).append(v);
		}
		return sb.toString();
	}

}
