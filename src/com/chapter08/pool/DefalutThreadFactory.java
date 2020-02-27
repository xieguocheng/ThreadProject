package com.chapter08.pool;

import java.util.concurrent.atomic.AtomicInteger;

import com.chapter08.pool.接口.ThreadFactory;

/** 
*
* @Description:线程工厂
* @Author:	XO
* @CreateDate:  2020年2月7日 上午11:37:04 
* 
*/
public class DefalutThreadFactory implements ThreadFactory {
	
	private static final AtomicInteger GROUP_COUNTER=new AtomicInteger(1);
	private static final AtomicInteger COUNTER=new AtomicInteger(0);
	
	private static final ThreadGroup group=new ThreadGroup("myThreadGroupPool-"+GROUP_COUNTER.getAndDecrement());

	@Override
	public Thread createThread(Runnable runnable) {
		return new Thread(group,runnable,"thread-pool-"+COUNTER.getAndDecrement());
	}
	
	
	
	
	
	

}
