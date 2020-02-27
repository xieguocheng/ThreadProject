package com.Test;

import java.util.stream.IntStream;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月11日 下午11:06:07 
* 
*/
public class LambdaTest {
	
	public static void main(String[] args) {
		
		//1、创建5个线程
		IntStream.range(0, 5).boxed().
			map(i->
					new Thread(()->System.out.println(Thread.currentThread().getName()))
				).forEach(Thread::start);
		
		//2、创建5个线程
		IntStream.range(0, 5).mapToObj(LambdaTest::createThread).forEach(Thread::start);
		
	}
	
	private static Thread createThread(final int intName){
		
		//System.out.println(intName);
		
		return new Thread(()->
		System.out.println(Thread.currentThread().getName())
		,"thread--"+intName);
		
	}

}
