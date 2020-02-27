package com.chapter08.pool;

import java.util.concurrent.TimeUnit;

import com.chapter08.pool.接口.ThreadPool;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月7日 下午1:24:58 
* 
*/
public class ThreadPoolTest {
	
	public static void main(String[] args) {
		final ThreadPool tp = new BasicThreadPool(2, 6, 4, 1000);
		for(int i=0;i<20;i++){
			tp.execute(()->{
					try {
						TimeUnit.SECONDS.sleep(10);
						System.out.println(Thread.currentThread().getName()+" is runing and done");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			});
		}
		
		for(;;){
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("===============");
			System.out.println("getActiveCount"+tp.getActiveCount());
		}
		
	}

}
