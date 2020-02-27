package com.chapter17.readWriteLock;

import com.chapter17.readWriteLock.接口.Lock;
import com.chapter17.readWriteLock.接口.ReadWriteLock;

/** 
 *
 * @Description:建议设置成包可见的，这样才可以不对外暴露更多的细节
 * @Author:	XO
 * @CreateDate:  2020年2月23日 下午7:52:29 
 * 
 */

public class ReadWriteLockImpl implements ReadWriteLock{

	private final  Object MUTEX = new Object();

	private int writingWriters = 0; //当前有多少个线程正在写入
	private int waitingWriters = 0; //当前有多少个线程正在等待写入

	private int readingReaders = 0; //当前有多少个线程正在读

	private boolean preferWriter;//read和write设置偏好

	public ReadWriteLockImpl() {
		this(true);
	}

	public ReadWriteLockImpl(boolean preferWriter){
		this.preferWriter=preferWriter;
	}

	@Override
	public Lock readLock() {
		return new ReadLock(this);
	}

	@Override
	public Lock writeLock() {
		return new WriteLock(this);
	}

	void incrementWritingWriters() {
		this.writingWriters++;
	}

	void incrementWaitingWriters(){
		this.waitingWriters++;
	}

	void incrementReadingReaders(){
		this.readingReaders++;
	}

	void decrementWritingWriters() {
		this.writingWriters--;
	}

	void decrementWaitingWriters(){
		this.waitingWriters--;
	}

	void decrementtReadingReaders(){
		this.readingReaders--;
	}



	@Override
	public int getWritingWriters() {
		return this.writingWriters;
	}

	@Override
	public int getReadingReaders() {
		return readingReaders;
	}

	@Override
	public int getWaitingWriters() {
		return waitingWriters;
	}

	Object getMutex(){
		return this.MUTEX;
	}

	public boolean getPreferWriter() {
		return preferWriter;
	}

	void changePrefer(boolean preferWriter){
		this.preferWriter=preferWriter;
	}
}


