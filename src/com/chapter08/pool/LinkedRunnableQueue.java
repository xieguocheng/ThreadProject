package com.chapter08.pool;

import java.util.LinkedList;

import com.chapter08.pool.接口.DenyPolicy;
import com.chapter08.pool.接口.RunnableQueue;
import com.chapter08.pool.接口.ThreadPool;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月7日 上午11:06:31 
* 
*/
public class LinkedRunnableQueue implements RunnableQueue {
	
	private final int limit;//队列容量限制
	
	private final DenyPolicy denyPolicy;
	
	private final LinkedList<Runnable> runnableList=new LinkedList<>();
	
	private final ThreadPool threadPool;

	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		//super();
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	@Override
	public void offer(Runnable runnable) {
		
		synchronized (runnableList) {
			if(runnableList.size()>=limit){
				//使用对应的拒绝策略
				denyPolicy.reject(runnable, threadPool);
			}else{
				//将任务加入到队尾，同时唤醒阻塞中的线程
				runnableList.add(runnable);
				runnableList.notifyAll();
			}
		}

	}

	@Override
	public Runnable take() {
		synchronized (runnableList) {
			while(runnableList.isEmpty()){
				try{
					//没有可以执行的任务，当前线程挂起，
					runnableList.wait();
				}catch(InterruptedException e){
					
				}
			}
		}
		return runnableList.removeFirst();
	}

	@Override
	public int size() {
		
		synchronized (runnableList) {
			return runnableList.size();
		}
		
	}

}
