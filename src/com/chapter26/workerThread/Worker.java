package com.chapter26.workerThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月29日 下午8:25:59 
* 
*/
public class Worker extends Thread{
	
	//流水传送带
    private final ProductionChannel channel;

    private final static Random random = new Random(System.currentTimeMillis());

    public Worker(String name, ProductionChannel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try{
            	
                Production production = channel.takeProduction();
                System.out.println(getName()+" process the "+production);
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
