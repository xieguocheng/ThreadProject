package com.chapter07.hook;

import java.util.concurrent.TimeUnit;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月20日 下午9:03:46 
 * 
 */
public class ThreadHook {
	
	/*
    7.2.1 Hook线程介绍
    Jvm进程的退出是由于JVM进程没有活跃的非守护线程，或者系统中断了信号。向JVM程序
	    注入多个Hook线程，在JVM进程退出的时候，Hook线程会启动执行，通过Runtime可以为
	    JVM注入多个Hook线程。
	 */
	public static void main(String[] args) {
		//main线程结束时会运行hook线程
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try{
					System.out.println("The hook thread 1 is running...");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 1 will exit.");
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try{
					System.out.println("The hook thread 2 is running...");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 2 will exit.");
			}
		});
		
		
		System.out.println("exit");
		
	}
	
	
	
	
	
}
