package com.chapter17.readWriteLock.接口;

import com.chapter17.readWriteLock.ReadWriteLockImpl;

/** 
*
* @Description:读写分离设计模式
* 当对共享资源访问时，直接加锁synchroniz太过于粗暴了
* @Author:	XO
* @CreateDate:  2020年2月23日 下午7:50:50 
* 
*/
public interface ReadWriteLock {

    Lock readLock();
    Lock writeLock();

    int getWritingWriters();  //获取当前有多少个线程正在执行写操作
    int getReadingReaders();  //获取当前有多少个线程政治等待获取ReadingReaders

    int getWaitingWriters();  //获取当前有多少个线程正在等待获取写入锁

    //工厂方法，创建ReadwriteLock
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }
    //工厂方法，创建ReadwriteLock
    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
