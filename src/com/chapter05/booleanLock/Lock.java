package com.chapter05.booleanLock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2019年12月24日 下午7:16:19 
 * 
 */
public interface Lock{
	void lock()throws InterruptedException;
	void lock(long mills)throws InterruptedException,TimeoutException;
	void unlock();
	List<Thread>getBlockedThreads();
}
