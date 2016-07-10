package org.appsugar.commons.concurrency.disrutpor;

import org.appsugar.commons.concurrency.BindedProcessorUnit;
import org.appsugar.commons.concurrency.ExecutionHandler;

/**
 * disruptor处理单元
 * @author NewYoung
 * 2016年7月10日下午3:45:49
 * @param <T>
 */
public class DisruptorProcessorUnit<T> implements BindedProcessorUnit<T> {
	private int position;
	private int cpuL3CachePadding;
	private ExecutionHandler<T> handler;

	private DisruptorBindedExecutor executor;

	public DisruptorProcessorUnit(ExecutionHandler<T> handler, DisruptorBindedExecutor executor, int position) {
		this.handler = handler;
		this.executor = executor;
		this.position = position;
	}

	@Override
	public ExecutionHandler<T> handler() {
		return handler;
	}

	@Override
	public void execute(T data) {
		executor.execute(handler, position, data);
	}
}
