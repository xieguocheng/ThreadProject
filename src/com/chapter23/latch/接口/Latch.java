package com.chapter23.latch.接口;

import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月29日 下午7:25:11 
* 
*/
public abstract class Latch{
	
	//添加valatile关键字，自我觉得更加保险
    protected volatile int  limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    //该方法会使得当前线程一致等待
    public abstract void await()throws InterruptedException;

    //带超时功能的
    public abstract void await(TimeUnit unit, long time) throws InterruptedException,WaitTimeoutException;

    //当任务线程完成工作之后调用该方法使得计数器减一
    public abstract void countDown();

    //获取当前还有多少个线程没有完成任务
    public abstract int getUnarrived();
}

