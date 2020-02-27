package com.chapter17.readWriteLock;

import com.chapter17.readWriteLock.接口.Lock;

/** 
*
* @Description:建议设置成包可见的，这样才可以不对外暴露更多的细节
* @Author:	XO
* @CreateDate:  2020年2月23日 下午7:53:36 
* 
*/

public class WriteLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMutex()) {
            try{
                readWriteLock.incrementWaitingWriters();
                while(readWriteLock.getReadingReaders()>0||
                        readWriteLock.getWritingWriters()>0){
                    readWriteLock.getMutex().wait();
                }
            }finally {
                this.readWriteLock.decrementWaitingWriters();
            }
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMutex()) {
            readWriteLock.decrementWritingWriters();
            readWriteLock.changePrefer(false);
            readWriteLock.getMutex().notifyAll();
        }
    }
}