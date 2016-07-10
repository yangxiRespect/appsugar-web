package org.appsugar.concurrency.disruptor;

import org.appsugar.commons.concurrency.BindedExecutor;
import org.appsugar.commons.concurrency.ExecutionHandler;
import org.appsugar.commons.concurrency.disrutpor.DisruptorBindedExecutor;
import org.junit.Test;

import junit.framework.TestCase;

public class DisruptorBindedExecutorTest extends TestCase {

	private BindedExecutor executor = new DisruptorBindedExecutor(2);

	private int times = 20000000;

	@Test
	public void testBindedPerformance() {
		ExecutionHandler<Integer> handler = e -> e++;
		for (int i = 0; i < times; i++) {
			executor.execute(handler, 0, 1);
		}
	}

	/**
	 * 单个线程向不同worker派发任务,性能开销大多在锁上
	 */
	@Test
	public void testUnBindedPerformance() {
		ExecutionHandler<Integer> handler = e -> e++;
		for (int i = 0; i < times; i++) {
			executor.execute(handler, 1);
		}
	}

}
