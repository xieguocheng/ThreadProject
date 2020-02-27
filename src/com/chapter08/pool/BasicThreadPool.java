package com.chapter08.pool;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.chapter08.pool.接口.DenyPolicy;
import com.chapter08.pool.接口.RunnableQueue;
import com.chapter08.pool.接口.ThreadFactory;
import com.chapter08.pool.接口.ThreadPool;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月7日 上午11:28:44 
* 
*/
public class BasicThreadPool extends Thread implements ThreadPool {
	
	private final int initSize;
	
	private final int maxSize;

	private final int coreSize;
	
	private int activeCount;
	
	//工厂
	private final ThreadFactory threadFactory;
	
	//任务队列
	private final RunnableQueue runnableQueue;
	
	//是否关闭
	private volatile boolean isShutdown =false;
	
	private final Queue<ThreadTask> threadQueue=new ArrayDeque<>();
	
	//默认拒绝策略是直接丢弃
	private final static DenyPolicy DEFAULT_DENYPOLICY=new DenyPolicy.DiscardDenyPolicy();
	
	//默认工厂
	private final static ThreadFactory DEFAULT_THREADFACTORY=new DefalutThreadFactory();
	
	private final long keepAliveTime;
	
	private final TimeUnit timeUnit;
	
	
	
	
	public BasicThreadPool(int initSize, int maxSize, int coreSize,int queueSize) {
		this(initSize,maxSize,coreSize,
				DEFAULT_THREADFACTORY,
				queueSize,
				DEFAULT_DENYPOLICY,
				10,TimeUnit.SECONDS);
	}
	
	

	public BasicThreadPool(int initSize, int maxSize, int coreSize, 
			ThreadFactory threadFactory,//工厂
			int limit,//任务队列大小
			DenyPolicy denyPolicy,//拒绝策略
		    long keepAliveTime, TimeUnit timeUnit) {
		
		//需要传入的参数
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		
		//默认参数
		this.threadFactory = threadFactory;
		this.runnableQueue=new LinkedRunnableQueue(limit, denyPolicy, this);
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		
		this.init();
	}
	
	



	private void init() {
		
		start();//线程池就是一个线程，启动线程池重写的run方法
		
		for(int i=0;i<initSize;i++){
			newThread();//线程池启动（也就是往线程池添加线程，用来运行任务）
		}
		
	}
	
	


	/**
	 * 用来维护线程数量，扩容，回收等工作
	 */
	@Override
	public void run() {
		while(!isShutdown&&!isInterrupted()){
			//1.
			try{
				timeUnit.sleep(keepAliveTime);
			}catch (InterruptedException e) {
				isShutdown=true;
				break;
			}
			//2.
			synchronized (this) {
				if(isShutdown){
					break;
				}
				//
				if(runnableQueue.size()>0&&activeCount<coreSize){
					for(int i=initSize;i<coreSize;i++){
						newThread();
					}
					continue;
				}
				//
				if(runnableQueue.size()>0&&activeCount<maxSize){
					for(int i=coreSize;i<maxSize;i++){
						newThread();
					}
				}
				//
				if(runnableQueue.size()==0&&activeCount<coreSize){
					for(int i=coreSize;i<activeCount;i++){
						removeThread();
					}
				}
				
			}
			
		}
	}



	private void newThread() {
		//1.真正的任务线程
		InternalTask internalTask = new InternalTask(runnableQueue);
		//2.线程工厂返回的线程还是原来的internalTask，只是经过加工，添加了线程的名称,组
		Thread createThread = this.threadFactory.createThread(internalTask);
		
		//TODO
		//System.out.println(internalTask+"==="+createThread.getThreadGroup());
		
		//将1，2结合
		ThreadTask threadTask = new ThreadTask(createThread, internalTask);//任务执行体
		threadQueue.offer(threadTask);
		this.activeCount++;
		createThread.start();//开启任务线程，跑的是internalTask中的run方法
		//internalTask.run();
		
	}
	
	private void removeThread(){
		ThreadTask remove = threadQueue.remove();
		remove.internalTask.stop();
		this.activeCount--;
	}



	/**
	 * 用来提交新添加的任务
	 */
	@Override
	public void execute(Runnable runnable) {
		if(this.isShutdown){
			throw new IllegalStateException("the threadPool is destroy");
		}
		this.runnableQueue.offer(runnable);
	}
	
	/**
	 * 内部类，其实就是internalTask和thread的一个组合
	 * @author asus
	 *
	 */
	private static class ThreadTask {
		
		Thread thread;
		InternalTask internalTask;//真正的任务执行体
		
		public ThreadTask(Thread thread,InternalTask internalTask){
			
			this.thread=thread;
			this.internalTask=internalTask;
			
		}
	}
	
	////////*****************************下面是常用的获取线程池参数***********************************////
	
	

	@Override
	public void shutdown() {
		synchronized (this) {
			if(isShutdown) return ;
			isShutdown=true;
			threadQueue.forEach(threadTask->{
				threadTask.internalTask.stop();
				threadTask.thread.interrupt();
			});
		}

	}

	@Override
	public int getInitSize() {
		if(isShutdown)
			throw new IllegalStateException("the thread pool is destroy");
		return this.initSize;
	}

	@Override
	public int getMaxSize() {

		if(isShutdown)
			throw new IllegalStateException("the thread pool is destroy");
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if(isShutdown)
			throw new IllegalStateException("the thread pool is destroy");
		return this.coreSize;
	}

	@Override
	public int getQueueSize() {
		if(isShutdown)
			throw new IllegalStateException("the thread pool is destroy");
		return runnableQueue.size();
	}

	@Override
	public int getActiveCount() {
		synchronized (this) {
			return this.activeCount;
		}
	}

	@Override
	public boolean isShutdown() {
		if(isShutdown)
			throw new IllegalStateException("the thread pool is destroy");
		return this.isShutdown;
	}

}
