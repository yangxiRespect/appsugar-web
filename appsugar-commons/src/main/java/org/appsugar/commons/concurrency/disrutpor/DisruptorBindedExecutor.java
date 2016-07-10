package org.appsugar.commons.concurrency.disrutpor;

import java.util.Arrays;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

import org.appsugar.commons.concurrency.BindedExecutor;
import org.appsugar.commons.concurrency.BindedProcessorUnit;
import org.appsugar.commons.concurrency.ExecutionHandler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * 绑定执行器disruptor实现
 * @author NewYoung
 * 2016年7月10日下午2:49:47
 */
public class DisruptorBindedExecutor implements BindedExecutor {

	public static final int defaultRingBufferSize = (int) Math.pow(2, 17);

	protected Disruptor<ExecutionEvent>[] disruptors;

	protected RingBuffer<ExecutionEvent>[] ringBuffers;

	protected int availableThreads;

	protected LongAdder sequence = new LongAdder();

	protected LongAdder unitPosition = new LongAdder();

	public DisruptorBindedExecutor() {
		this(Runtime.getRuntime().availableProcessors());
	}

	@SuppressWarnings("unchecked")
	public DisruptorBindedExecutor(int availableThreads) {
		this.availableThreads = availableThreads;
		disruptors = new Disruptor[availableThreads];
		ringBuffers = new RingBuffer[availableThreads];
		for (int i = 0; i < availableThreads; i++) {
			Disruptor<ExecutionEvent> disruptor = new Disruptor<>(ExecutionEvent::new, ringBufferSize(),
					newThreadFactor());
			disruptor.handleEventsWith(eventHandler());
			disruptors[i] = disruptor;
			ringBuffers[i] = disruptor.start();
		}
	}

	@Override
	public <T> void execute(ExecutionHandler<T> handler, T data) {
		sequence.increment();
		int position = (int) Math.abs(sequence.longValue() % availableThreads);
		execute(handler, position, data);
	}

	@Override
	public int availableThreads() {
		return availableThreads;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void execute(ExecutionHandler<T> handler, int position, T data) {
		RingBuffer<ExecutionEvent> ringBuffer = ringBuffers[position];
		long next = ringBuffer.next();
		ExecutionEvent event = ringBuffer.get(next);
		event.data = data;
		event.handler = (ExecutionHandler<Object>) handler;
		ringBuffer.publish(next);
	}

	@Override
	public <T> BindedProcessorUnit<T> createBindedProcessorUnit(ExecutionHandler<T> handler) {
		unitPosition.increment();
		return createBindedProcessorUnit(handler, (int) (unitPosition.longValue() % availableThreads));
	}

	@Override
	public <T> BindedProcessorUnit<T> createBindedProcessorUnit(ExecutionHandler<T> handler, int position) {
		return new DisruptorProcessorUnit<>(handler, this, position);
	}

	@Override
	public void shutdown() {
		Arrays.asList(disruptors).stream().forEach(d -> d.shutdown());
	}

	protected ThreadFactory newThreadFactor() {
		return r -> {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		};
	}

	protected int ringBufferSize() {
		return defaultRingBufferSize;
	}

	protected EventHandler<ExecutionEvent> eventHandler() {
		return (e, s, b) -> {
			Object data = e.data;
			ExecutionHandler<Object> handler = e.handler;
			e.reset();
			handler.handle(data);
		};
	}

}
