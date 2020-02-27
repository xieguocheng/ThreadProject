package com.chapter07.hook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2019年12月30日 下午1:08:02 
* 
*/
public class CaptureThreadException {
	
	

	public static void main(String[] args) {
		/*ThreadGroup main = Thread.currentThread().getThreadGroup();
		System.out.println(main.getName());
		System.out.println(main.getParent().getName());
		System.out.println(main.getParent().getParent());*/
		
		Thread.setDefaultUncaughtExceptionHandler((t,e)->{
			System.err.println(t.getName()+" occur exception");
			e.printStackTrace();
		});
		final Thread thread = new Thread(()->{
			try{
				TimeUnit.SECONDS.sleep(2);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(1/0);
		},"test-thread");
		thread.start();
		
		ThreadGroup t=new ThreadGroup("testGroup");
		
		/*final List<String> list=new ArrayList<String>();
		list.add("af");
		System.out.println(list.toString());
		list.add("afsd");
		System.out.println(list.toString());*/
		
		
	}
}
