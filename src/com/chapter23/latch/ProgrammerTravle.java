package com.chapter23.latch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.chapter23.latch.接口.Latch;

/** 
*
* @Description:程序测试齐心协力打开门阀
* @Author:	XO
* @CreateDate:  2020年2月29日 下午7:26:59 
* 
*/
public class ProgrammerTravle extends Thread{
	
	//重点门阀
    private final Latch latch;
    
    private final String programmer;
    
    private final String transportation;

    public ProgrammerTravle(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer+" start take the transportation["+transportation+"]");
        try{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer+" arrived by "+transportation);
        latch.countDown();
    }
    
    
    
}
