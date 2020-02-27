package com.chapter17.readWriteLock;

import com.chapter17.readWriteLock.接口.Lock;

/** 
 *
 * @Description:建议设置成包可见的，这样才可以不对外暴露更多的细节
 * @Author:	XO
 * @CreateDate:  2020年2月23日 下午7:54:08 
 * 
 */

public class ReadLock implements Lock{
	private final ReadWriteLockImpl readWriteLock;

	public ReadLock(ReadWriteLockImpl readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	@Override
	public void lock() throws InterruptedException {
		synchronized (readWriteLock.getMutex()){
			/*
                	对这段逻辑的分析：
                    1.如果你正在写的大于一，我的确应该等待
                    2.如果你偏好于写，同时等待的多于一，那么：
                                                          我读这边应该等待
			 */
			while(readWriteLock.getWritingWriters()>0||
					(readWriteLock.getPreferWriter()&&
							readWriteLock.getWaitingWriters()>0)){
				readWriteLock.getMutex().wait();
			}
			readWriteLock.incrementReadingReaders();
		}
	}

	@Override
	public void unlock() {
		synchronized (readWriteLock.getMutex()) {    
			
			readWriteLock.decrementtReadingReaders();//reading减一
			readWriteLock.changePrefer(true);//将preferWriter设置为true，可以使得writer获得更多的机会
			readWriteLock.getMutex().notifyAll();//通知唤醒与mutex关联的的monitor waitset中的线程
			
		}
	}
}