package org.appsugar.commons.concurrency;

/**
 * 绑定处理单元
 * 把所有数据交由指定线程处理
 * @author NewYoung
 * 2016年7月10日下午2:40:40
 */
public interface BindedProcessorUnit<T> {

	/**
	 * 获取处理handler
	 */
	public ExecutionHandler<T> handler();

	/**
	 * 执行数据 
	 */
	public void execute(T data);
}
