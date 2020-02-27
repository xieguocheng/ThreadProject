package com.Test;

import java.util.stream.IntStream;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月12日 下午11:22:06 
 * 
 */
public class YieldThread {

	public static void main(String[] args) {
		//2、创建5个线程
		IntStream.range(0, 2).mapToObj(YieldThread::createThread).forEach(Thread::start);
		
		
	}

	private static Thread createThread( int index){
		Thread thread = new Thread(()->{
			if(index==0){
				Thread.yield();//使得当前Thread从running状态切换到runnable状态
			}
			boolean interrupted = Thread.currentThread().isInterrupted();
			System.out.println(index+"---"+interrupted);
		});

		
		
		
		return thread;

	}

}
