package com.chapter08.pool;

import com.chapter08.pool.接口.RunnableQueue;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:49:12 
* 
*/
public class InternalTask implements Runnable{
	
	private final RunnableQueue runnableQueue;
	
	private volatile boolean running=true;
	
	public InternalTask(RunnableQueue runnableQueue) {
		this.runnableQueue=runnableQueue;
	}

	@Override
	public void run() {
		
		//当前任务是running状态，并且没有被中断
		while(running&&!Thread.currentThread().isInterrupted()){
			try{
				Runnable task = runnableQueue.take();
				System.out.println(Thread.currentThread().getName()+"#任务执行开始#");
				task.run();
				System.out.println(Thread.currentThread().getName()+"#任务执行结束#");
			}catch (Exception e) {
				running=false;
				break;
			}
		}
		
	}
	
	public void stop(){
		
		this.running=false;
	}

}
