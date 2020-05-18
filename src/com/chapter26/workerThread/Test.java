package com.chapter26.workerThread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午8:28:26 
 * 
 */
public class Test {
	public static void main(String[] args) {

		
		//流水线上5个工人(线程)
		final ProductionChannel channel = new ProductionChannel(5);

		AtomicInteger productionNo = new AtomicInteger();

		//流水线上8个工作人员(线程)不断提供代加工的产品
		IntStream.range(1,8).forEach(i->new Thread(()->{
			while(true){
				channel.offerProduction(new Production(productionNo.getAndIncrement()));

				try{
					TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start());
	
	}

}
