package com.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.stream.events.StartDocument;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2019年12月19日 下午3:17:18 
* 
*/
public class ThreadJoin {
	
	public static void main(String[] args) throws InterruptedException {
		
		//1.将数据收集进一个列表(Stream 转换为 List，允许重复值，有顺序)
		//创建流
		/*Stream<String> language = Stream.of("java", "python", "C++","php","java");
		List<String> listResult = language.collect(Collectors.toList());
		listResult.forEach(System.out::println);*/

		List<Thread> threads = IntStream .range(1, 3).mapToObj(ThreadJoin::create).
				collect(Collectors.toList());
		
		threads.forEach(Thread::start);
		
		for(Thread thread: threads){
			thread.join();
		}
		
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"#"+i);
			shortSleep();
		}
		
	}
	


	public static Thread create(int seq){
		System.out.println("创建线程："+seq);
		return new Thread(()->{
			for (int i=0;i<10;i++){
				System.out.println("线程"+Thread.currentThread().getName()+"#"+i);
				shortSleep();
			}
		},String.valueOf(seq));
	}

	private static void shortSleep() {

		try{
			TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
