package com.chapter19.future;

import java.util.concurrent.TimeUnit;

import com.chapter19.future.接口.Future;
import com.chapter19.future.接口.FutureService;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月27日 下午9:08:57 
* 
*/
public class Test {
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		/*//1.无返回值
		//不需要返回值的futureservice
		FutureService<Void, Void> service = FutureService.newService();
		//submit是立即返回的方法
		Future<?> future = service.submit(()->{
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("i am finish done.");
		});
		
		future.get();*/
		
		
		//2.有返回值
		/*FutureService<String, Integer> service1 = FutureService.newService();
		//submit是立即返回的方法
		Future<?> future1 = service1.submit(input->{
			//以下重写了task<>类的get方法
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return input.length();
		},"hello");
		
		System.out.println(future1.get());*/
		
		//3.回调接口
		FutureService<String, Integer> service2 = FutureService.newService();
		//submit是立即返回的方法
		service2.submit(input->{
			//以下重写了task<>类的get方法
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return input.length();
		},"hello",System.out::println);
		
		System.out.println("-----");
		
	}

}
