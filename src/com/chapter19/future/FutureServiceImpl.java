package com.chapter19.future;

import java.util.concurrent.atomic.AtomicInteger;

import com.chapter19.future.接口.Calback;
import com.chapter19.future.接口.Future;
import com.chapter19.future.接口.FutureService;
import com.chapter19.future.接口.Task;

/** 
 *
 * @Description:在任务结束后，会调用该类的finish方法。将isDone置为true并且为result赋值(线程执行结果)。
			调用者可以使用get方法来得到结果。
 * @Author:	XO
 * @CreateDate:  2020年2月26日 上午9:37:21 
 * 
 */
public class FutureServiceImpl<IN,OUT> implements FutureService<IN,OUT> {
	
	private final static String FUTURE_THREAD_PREFIX="FUTURE-";
	
	//原子性
	private final AtomicInteger nextCounter = new AtomicInteger(0);
	
	private String getNextName(){
		return FUTURE_THREAD_PREFIX+nextCounter.getAndIncrement();
	}
 
	@Override
	public Future<?> submit(Runnable runnable) {
		final FutureTask<Void> future = new FutureTask<Void>();
		//新建一个线程，调用run也就是任务
		new Thread(()->{
			runnable.run();
			future.finish(null);
		},getNextName()).start();
		return future;
	}

	@Override
	public Future<?> submit(Task<IN, OUT> task, IN input) {
		final FutureTask<OUT> future = new FutureTask<OUT>();
		new Thread(()->{
			OUT result  =task.get(input);
			future.finish(result);
		},getNextName()).start();
		return future;
	}

	@Override
	public Future<?> submit(Task<IN, OUT> task, IN input, Calback<OUT> callback) {
		final FutureTask<OUT> future = new FutureTask<>();
		new Thread(()->{
			OUT result  =task.get(input);
			future.finish(result);
			
			//执行回调接口callback
			if(null!=callback){
				callback.call(result);
			}
			
		},getNextName()).start();
		return future;
	}
}
 

