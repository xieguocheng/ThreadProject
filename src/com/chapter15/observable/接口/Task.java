package com.chapter15.observable.接口;
/** 
*
* @Description:任务体
* @Author:	XO
* @CreateDate:  2020年2月23日 下午1:05:40 
* 
*/
public interface Task <T>{
    T call();//真正执行任务的方法
}
