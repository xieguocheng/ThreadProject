package com.chapter08.pool.接口;
/** 
*
* @Description:线程池
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:25:03 
* 
*/
public interface ThreadPool {
	
	//提交任务到线程池
	void execute(Runnable runnable);
	
	void shutdown();
	
	int getInitSize();
	
	int getMaxSize();
	
	int getCoreSize();
	
	int getQueueSize();
	
	int getActiveCount();
	
	boolean isShutdown();
	
	

}
