package com.chapter19.future.接口;

import com.chapter19.future.FutureServiceImpl;

/** 
 *
 * @Description:该接口用于提交任务，会返回一个Future用来稍后查询任务结果
 * @Author:	XO
 * @CreateDate:  2020年2月26日 上午9:35:46 
 * 
 */
public interface FutureService<In,OUT> {
	
	//无返回结果
	Future<?> submit(Runnable  runnable);
	
	//有返回结果
	Future<?> submit(Task<In,OUT> task,In input);
	
	//增加回调接口callback，当任务执行完成后会执行callback
	Future<?> submit(Task<In,OUT> task,In input,Calback<OUT> callback);
	
	static <T,R> FutureService<T,R> newService(){
		return new FutureServiceImpl<T, R>();
	}
}

