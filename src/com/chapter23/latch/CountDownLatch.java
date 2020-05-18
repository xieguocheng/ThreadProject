package com.chapter23.latch;

import java.util.concurrent.TimeUnit;

import com.chapter23.latch.接口.Latch;
import com.chapter23.latch.接口.WaitTimeoutException;

/** 
 *
 * @Description://无限等待CountDownLatch实现
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午7:24:26 
 * 
 */
public class CountDownLatch extends Latch{

	public CountDownLatch(int limit) {
		super(limit);
	}

	@Override
	public void await() throws InterruptedException {
		synchronized (this) {
			while (limit > 0) {
				this.wait();
			}
		}
	}

	/*
        这个写法在写改写sysn时用过了。
	 */
	@Override
	public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
		if (time < 0) {
			throw new IllegalArgumentException("The time is invalid.");
		}

		long remainingNanos = unit.toNanos(time);
		final long endNanos = System.nanoTime()+ remainingNanos;
		synchronized (this) {
			while (limit > 0) {
				if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
					throw new WaitTimeoutException("The wait time over specify time.");
				}

				this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
				remainingNanos=endNanos-System.nanoTime();
			}
		}
		
	}

	@Override
	public void countDown() {
		synchronized (this) {
			if (limit <= 0) {
				throw new IllegalArgumentException("all of task already arrived");
			}
			limit--;
			this.notifyAll();
		}
	}

	@Override
	public int getUnarrived() {
		return limit;
	}
}
