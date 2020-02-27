package com.chapter05.booleanLock;
/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月20日 下午4:35:11 
* 
*/
import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
public class BooleanLockTest {
	
	//定义BooleanLock
	private final Lock lock = new BooleanLock();
	
	//使用try finally 语句确保lock每次都能正确的释放
	public void synMethod(){
		try{
			lock.lock();  
			int randomInt = current().nextInt(10);
			System.out.println(currentThread() + "get the lock.");
			TimeUnit.SECONDS.sleep(randomInt);
		} catch(InterruptedException e) {
			System.out.println("==InterruptedException==");
			e.printStackTrace();
		} finally {
			lock.unlock(); //释放锁
		}
	}
	public static void main(String args[]) throws InterruptedException{
		/*BooleanLockTest blt = new BooleanLockTest();
		//定义一个线程并且启动
		IntStream.range(0, 10)
		  .mapToObj(i -> new Thread(blt :: synMethod))
		  .forEach(Thread :: start);*/
		
		
	    BooleanLockTest blt = new BooleanLockTest();
		new Thread(blt :: synMethod, "T1").start();
		TimeUnit.MICROSECONDS.sleep(2);
		Thread t2 = new Thread(blt :: synMethod, "T2");
		t2.start();
		TimeUnit.MICROSECONDS.sleep(10);
		t2.interrupt(); //T2线程运行10毫秒后主动将其打断

	}

}
