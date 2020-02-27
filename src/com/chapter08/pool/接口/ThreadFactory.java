package com.chapter08.pool.接口;
/** 
*
* @Description:线程工厂
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:39:19 
* 
*/
@FunctionalInterface
public interface ThreadFactory {
	
	Thread createThread(Runnable runnable);

}
