package com.Test;

import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2019年12月19日 下午4:46:06 
* 
*/
public class ThisMonitor {
	
	public synchronized void method1(){
		System.out.println(Thread.currentThread().getName()+"method11");
		try{
			/*thisMonitor.wait();
			thisMonitor.notifyAll();*/
			TimeUnit.MINUTES.sleep(10);
			
		}catch (InterruptedException e) {
			System.out.println("method1 be interrupted");
			e.printStackTrace();
		}
	}
	public  synchronized void method2(){
			System.out.println(Thread.currentThread().getName()+"method22");
			try{
				TimeUnit.MINUTES.sleep(10);
			}catch (InterruptedException e) {
				System.out.println("method2 be interrupted");
				e.printStackTrace();
			}
	}
	
	static ThisMonitor thisMonitor=new ThisMonitor();
	public static void main(String[] args) throws InterruptedException {
		/*Thread thread1 = new Thread(thisMonitor::method1,"t1");
		thread1.start();
		Thread thread2 = new Thread(thisMonitor::method1,"t2");
		thread2.start();*/
		Thread thread1 = new Thread(thisMonitor::method1,"t1");
		thread1.start();
		TimeUnit.MILLISECONDS.sleep(2);
		
		//thread1.notify();只能在synchronization中使用notify，wait
		System.out.println("--------");
		Thread thread2 = new Thread(thisMonitor::method2,"t2");
		thread2.start();
		System.out.println("--------");
		
		TimeUnit.MILLISECONDS.sleep(2);
		System.out.println(thread2.getState());//
		thread1.interrupt();
		System.out.println(thread2.isInterrupted());
		System.out.println(thread2.getState());//还是处于阻塞状态
		 
		
		
		
		
	}

}
