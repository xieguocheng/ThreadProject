package com.chapter05.booleanLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2019年12月24日 下午7:35:01 
 * 
 */
public class BooleanLock implements Lock{
	private boolean locked;
	private Thread currentThread; //当前持有锁的线程
	private final List<Thread> blockedThreads = new ArrayList<>(); //等待获取锁的线程


	@Override
	public void lock() throws InterruptedException {
		synchronized (this) {
			while (locked) {
				if (!blockedThreads.contains(Thread.currentThread())) {
					blockedThreads.add(Thread.currentThread());
				}
				try {
					this.wait();
				} catch (InterruptedException e) {
					blockedThreads.remove(Thread.currentThread());
					throw e;
				}
			}
			locked = true;
			currentThread = Thread.currentThread();
			blockedThreads.remove(currentThread);
		}
	}
	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		synchronized (this) {
			if (mills <= 0) {
				this.lock();
			} else {
				long remainMills = mills;
				long endTime = System.currentTimeMillis() + remainMills; //截至时刻
				while (locked) {
					if (remainMills <= 0) { //剩余等待时间
						throw new TimeoutException("cannot get lock during " + remainMills);
					}
					if (!blockedThreads.contains(Thread.currentThread())) {
						blockedThreads.add(Thread.currentThread());
					}
					this.wait(remainMills); //等待一定时间
					remainMills = endTime - System.currentTimeMillis(); //重新计算等待时间：当前时间点与while执行之前的时间点之差。
				}
				locked = true;
				currentThread = Thread.currentThread();
				blockedThreads.remove(currentThread);
			}
		}
	}
	@Override
	public void unlock() {
		synchronized (this) {
			if (currentThread == Thread.currentThread()) {
				locked = false;
				//TODO
				Optional.of(Thread.currentThread().getName() + "release the lock.")
					.ifPresent(System.out :: println);
				this.notifyAll();
			}
		}
	}
	@Override
	public List<Thread> getBlockedThreads() {
		return Collections.unmodifiableList(blockedThreads);
	}

}
