package com.chapter21.threadLocal;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月28日 下午12:14:38 
 * 
 */
public class Test {

	public static void main(String[] args) {

		ThreadLocal<Integer> l = new ThreadLocal<>();
		
		System.out.println(l.get());

		IntStream.range(0, 10).forEach(i->new Thread(()->{
			try{
				//每个线程的i值完全不同互相独立，不影响
				l.set(i);
				System.out.println(Thread.currentThread()+ " set i "+l.get());
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread()+ " get i "+l.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start());
		
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println(l.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
