package com.chapter24.perMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午7:50:03 
 * 
 */
public class MessageHandler {
	
	//新建一个线程池
	private final ExecutorService pool = Executors.newFixedThreadPool(5);
	
	public void request(final Message message){
		
		pool.execute(new Runnable() {
			@Override
			public void run() {
				String result = message.getMessage();
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + ">>>>>" + result);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
