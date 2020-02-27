package com.chapter18.immutable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月25日 下午10:09:21 
 * 
 */
/*
第18章 不可变对象设计模式
这个不能保证几个线程对同一个数据进行操作，只能说各自对各自的备份操作吧
 */

public final class IntegerAccumulator{
	
	//划重点，使用final修饰
	private final int init;

	public IntegerAccumulator(int init) {
		this.init = init;
	}

	public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
		this.init = accumulator.getValue()+init;
	}

	//划重点，每次返回一个新的对象，保证新的对象的init参数不可变，（final修饰）
	public IntegerAccumulator add(int i) {
		return new IntegerAccumulator(this, i);
	}

	public int getValue() {
		return this.init;
	}

	private static void slowly(){
		try{
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String arg[]){
		IntegerAccumulator accumulator = new IntegerAccumulator(0);
		IntStream.range(0,3).forEach(i->new Thread(()->{
			int inc = 0;
			while (true) {
				int oldValue = accumulator.getValue();
				int result = accumulator.add(inc).getValue();
				System.out.println(oldValue+"+"+inc+"="+result);
				if (inc + oldValue != result) {
					System.out.println("Error...");
				}
				inc++;
				slowly();
			}
		}).start());
	}
}