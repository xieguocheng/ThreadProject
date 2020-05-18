package com.chapter23.latch.接口;
/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午7:25:54 
 * 
 */
/*
第23章 Latch 设计模式
若干线程并发执行某个特定的任务，然后等到所有的子任务都执行结束之后再统一汇总。
23.2 CountDownLatch程序实现
23.2.1 无限等待的Latch
 */
public class WaitTimeoutException extends Exception{
	public WaitTimeoutException(String msg) {
		super(msg);
	}
}
