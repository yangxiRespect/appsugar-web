package org.appsugar.commons.concurrency;

/**
 * 执行处理器
 * @author NewYoung
 * 2016年7月10日下午2:16:39
 * @param <T>
 */
public interface ExecutionHandler<T> {
	public void handle(T data) throws Exception;
}
