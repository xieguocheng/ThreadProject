package com.chapter08.pool.接口;
/** 
*
* @Description:任务队列，阻塞队列，用于缓存提交到线程池的所有任务，
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:36:56 
* 
*/
public interface RunnableQueue {
	
	void offer(Runnable runnable);//add runnable to queue
	
	Runnable take();//get runnable
	
	int size();

}
