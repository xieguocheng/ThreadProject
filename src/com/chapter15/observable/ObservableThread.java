package com.chapter15.observable;

import com.chapter15.observable.接口.Observable;
import com.chapter15.observable.接口.Task;
import com.chapter15.observable.接口.TaskLifeCycle;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月23日 下午1:02:58 
 * 
 */
/*
突然发现Observable中的start方法有点可怕的啊，这种写法，我感觉我是还每吃透
 */
public class ObservableThread<T> extends Thread implements Observable{
	
	private final TaskLifeCycle<T> lifeCycle;
	private final Task<T> task;
	private Cycle cycle;

	public ObservableThread(Task<T> task){
		this(new TaskLifeCycle.EmptyLifeCycle<>(),task);
	}

	public ObservableThread(TaskLifeCycle<T> lifeCycle,Task<T> task){

		super();

		if(task==null)
			throw new IllegalArgumentException("The task is required.");

		this.lifeCycle=lifeCycle;
		this.task=task;
	}

	public final void run(){
		this.update(Cycle.STARTED,null,null);
		try{
			this.update(Cycle.RUNNING,null,null);

			//调用真正的task，同时接收返回值进行update，也就是观察者模式的核心，进行通知
			T result = this.task.call();

			this.update(Cycle.DONE,result,null);
		} catch (Exception e){
			this.update(Cycle.ERROR,null,e);
		}
	}

	private void update(Cycle cycle, T result, Exception e){
		this.cycle = cycle;
		if (lifeCycle == null) {
			return;
		}

		try {
			switch (cycle) {
			case STARTED:
				this.lifeCycle.onStart(currentThread());
				break;
			case RUNNING:
				this.lifeCycle.onRunning(currentThread());
				break;
			case DONE:
				this.lifeCycle.onFinish(currentThread(), result);
				break;
			case ERROR:
				this.lifeCycle.onError(currentThread(), e);
				break;
			}
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Override
	public Cycle getCycle(){
		return this.cycle;
	}
}
