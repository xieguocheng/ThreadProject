package com.chapter08.pool.接口;
/** 
*
* @Description:异常处理
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:44:30 
* 
*/
public class RunnableDenyException  extends RuntimeException{
	
	public RunnableDenyException(String message){
		super (message);
	}

}
