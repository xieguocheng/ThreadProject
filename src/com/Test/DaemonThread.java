package com.Test;
/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月11日 下午11:36:39 
* 
*/
public class DaemonThread {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(()->{
			while(true){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		thread.setDaemon(true);//thread设置为守护线程，所以当main线程结束时会自动结束生命周期
		
		thread.start();
		Thread.sleep(2_000L);
		System.out.println("main over");
	}

}
