package org.appsugar.commons.concurrency;

/**
 * 执行器
 * @author NewYoung
 * 2016年7月10日下午2:17:43
 */
public interface Executor {
	/**
	 * 把任务交给一个worker执行
	 * worker线程接收到任务后会调用 {@link ExecutionHandler#handle(T)}
	 */
	public <T> void execute(ExecutionHandler<T> handler, T data);

	/**
	 * 关闭
	 */
	public void shutdown();
}
