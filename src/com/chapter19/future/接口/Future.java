package com.chapter19.future.接口;
/** 
*
* @Description:提交任务后会返回该接口，用于查询执行结果。
* @Author:	XO
* @CreateDate:  2020年2月25日 下午10:18:52 
* 
*/
public interface Future<T> {
	
    T get() throws InterruptedException;
    
    boolean done();
}

