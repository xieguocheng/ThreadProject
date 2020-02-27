package com.Test;
/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月25日 下午4:15:18 
 * 
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest implements Callable<String>{
	
	/**
	 * ThreadLocal是Java里一种特殊的变量。每个线程都有一个ThreadLocal就是每个线程都拥有了自己独立的一个变量，
	 * 竞争条件被彻底消除了。它是为创建代价高昂的对象获取线程安全的好方法，比如你可以用ThreadLocal让SimpleDateFormat变成线程安全的，
	 * 因为那个类创建代价高昂且每次调用都需要创建不同的实例所以不值得在局部范围使用它，如果为每个线程提供一个自己独有的变量拷贝
	 * ，将大大提高效率。首先，通过复用减少了代价高昂的对象的创建个数。其次，你在没有使用高代价的同步或者不变性的情况下获得了线程安全。
	 */
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@ Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public static void main(String[] args) {

		ExecutorService threadPool=Executors.newSingleThreadExecutor();
		
		//两个方法都可以向线程池提交任务，execute()方法的返回类型是void，它定义在Executor接口中,
		//而submit()方法可以返回持有计算结果的Future对象，它定义在ExecutorService接口中，它扩展了Executor接口，
		//其它线程池类像ThreadPoolExecutor和ScheduledThreadPoolExecutor都有这些方法。
		
		//submit启动多线程
		Future<String> future=threadPool.submit(new CallableTest());
		//execute启动多线程
		threadPool.execute(()->{
			System.out.println("runable");
		});
		try{
			System.out.println("waiting thread to finish");
			System.out.println(future.get());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * Callable的 call() 方法可以返回值和抛出异常，而Runnable的run()方法没有这些功能。Callable可以返回装载有计算结果的Future对象
	 */
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return "创建多线程方法三";
	}
}
