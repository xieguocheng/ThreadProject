package com.chapter19.future.接口;
/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月27日 下午9:40:11 
* 
*/
public interface Calback<T> {
	//任务完成后会调用该方法，其中T为任务执行后的结果
	void call(T t);
}
