package org.appsugar.commons.concurrency;

/**
 * 绑定执行器,能把处理器绑定在指定线程执行
 * @author NewYoung
 * 2016年7月10日下午2:36:49
 */
public interface BindedExecutor extends Executor {
	/**
	 * 可用线程数
	 */
	public int availableThreads();

	/**
	 *把数据交给指定线程处理
	 *@param position  必须大于0 小于 availableThreads
	 */
	public <T> void execute(ExecutionHandler<T> handler, int position, T data);

	/**
	 * 创建处理执行单元绑定到一个特定线程上 
	 */
	public <T> BindedProcessorUnit<T> createBindedProcessorUnit(ExecutionHandler<T> handler);

	/**
	 * 创建处理执行单元绑定到指定线程上
	 */
	public <T> BindedProcessorUnit<T> createBindedProcessorUnit(ExecutionHandler<T> handler, int position);
}
