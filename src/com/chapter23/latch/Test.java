package com.chapter23.latch;

import java.util.concurrent.TimeUnit;

import com.chapter23.latch.接口.Latch;
import com.chapter23.latch.接口.WaitTimeoutException;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午7:28:15 
 * 
 */
public class Test {
	
	public static void main(String[] args) {
		
		Test.test();
		
		Test.test2();
		
	}
	
	public static void test(){
		Latch latch = new CountDownLatch(4);
		new ProgrammerTravle(latch,"A","a").start();
		new ProgrammerTravle(latch,"B","b").start();
		new ProgrammerTravle(latch,"C","c").start();
		new ProgrammerTravle(latch,"D","d").start();

		try {
			latch.await();
			System.out.println("==all of programmer arrived==");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void test2(){
		Latch latch = new CountDownLatch(4);
		new ProgrammerTravle(latch,"A","a").start();
		new ProgrammerTravle(latch,"B","b").start();
		new ProgrammerTravle(latch,"C","c").start();
		new ProgrammerTravle(latch,"D","d").start();

		try {
			latch.await(TimeUnit.SECONDS,5);
			System.out.println("==all of programmer arrived==");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (WaitTimeoutException e) {
			e.printStackTrace();
		}
	}
}
