package com.Test;

import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月19日 下午8:40:13 
* 
*/
public class Mutex {
	
	private final static Object MUTEX=new Object();
	
	public void accessResource(){
		synchronized (MUTEX) {
			
			try {
				System.out.println(Thread.currentThread().getName());
				TimeUnit.MINUTES.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		final Mutex mutex=new Mutex();
		for(int i=0;i<5;i++){
			new Thread(mutex::accessResource).start();
		}
	}

}
