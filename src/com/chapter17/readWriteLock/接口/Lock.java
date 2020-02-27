package com.chapter17.readWriteLock.接口;
/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月23日 下午7:49:30 
* 
*/
public interface Lock{
    void lock() throws InterruptedException;
    void unlock();
}
