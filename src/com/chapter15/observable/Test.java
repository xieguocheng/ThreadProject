package com.chapter15.observable;

import java.util.concurrent.TimeUnit;

import com.chapter15.observable.接口.Observable;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月23日 下午1:09:07 
 * 
 */
public class Test{


	public static void main(String[] args) {
		Observable observableThread = new ObservableThread<>(()->{
			try{
				TimeUnit.SECONDS.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("finshed done");
			return null;
		});
		
		observableThread.start();

	}


}
