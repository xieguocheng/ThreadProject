package com.chapter19.future;

import com.chapter19.future.接口.Future;

/** 
 *
 * @Description:
	Future设计模式
	如果有任务执行需要比较长的时间，通常需要等待任务执行结束或者出错才能返回结果，在此期间调用者只能陷入阻塞苦苦等待，对此，
	Future设计模式提供了一种凭据式解决方案。可以先提交任务，立即返回一个凭据，调用者可稍后凭借凭借查询执行结果。
 * @Author:	XO
 * @CreateDate:  2020年2月25日 下午10:17:41 
 * 
 */
public class FutureTask<T> implements Future<T> {
	
	private T result;
	
	private boolean isDone = false;
	
	private final  Object LOCK = new Object();
	
	@Override
	public T get() throws InterruptedException {
		synchronized (LOCK){
			while(!isDone){
				LOCK.wait();
			}
		}
		return result;
	}
	
	public  void finish(T result){
		synchronized (LOCK){
			if(isDone){
				return;
			}
			this.result=result;
			this.isDone=true;
			LOCK.notifyAll();
		}
	}
	@Override
	public boolean done() {
		return isDone;
	}
}

