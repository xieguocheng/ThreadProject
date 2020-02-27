package com.chapter19.future.接口;
/** 
*
* @Description:如果执行的任务有返回值，则需要使用该接口提交任务。
* @Author:	XO
* @CreateDate:  2020年2月26日 上午9:35:19 
* 
*/
public interface Task<IN,OUT> {
    OUT get(IN input);
}