package com.Test;

import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月13日 上午12:09:35 
* 
*/
public class Test {
	

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.interrupted());
		System.out.println(Thread.currentThread().isInterrupted());
		Thread.currentThread().interrupt();
		//System.out.println(Thread.interrupted());
		System.out.println(Thread.currentThread().isInterrupted());
		Thread.currentThread().interrupt();
		
		System.out.println(Thread.interrupted());//当前线程被打断过，第一次调用会返回true，
		
		System.out.println(Thread.currentThread().isInterrupted());
		
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("afsdafsdfdas");
		}
		
		Thread t = new Thread(()->{
			
			while(true){
				System.out.println(Thread.interrupted());
			}
		});
		
		//t.setDaemon(true);
		
		t.start();
		
		TimeUnit.MICROSECONDS.sleep(1);
		
		t.interrupted();
		
		Thread[] list = new Thread[1];
		new ThreadGroup("1").enumerate(list);
		
		
	}

}
