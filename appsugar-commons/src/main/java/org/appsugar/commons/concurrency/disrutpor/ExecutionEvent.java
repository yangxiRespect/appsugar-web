package org.appsugar.commons.concurrency.disrutpor;

import org.appsugar.commons.concurrency.ExecutionHandler;

/**
 * 执行事件
 * @author NewYoung
 * 2016年7月10日下午2:48:08
 */
public class ExecutionEvent {
	public Object data;
	public ExecutionHandler<Object> handler;

	public void reset() {
		data = null;
		handler = null;
	}
}
