package com.chapter14.singleton;
/** 
 *
 * @Description:
 * 懒汉式+同步方法，线程安全
 * 缺点：同一时间只能够被一个线程访问
 * @Author:	XO
 * @CreateDate:  2020年2月6日 下午8:43:55 
 * 
 */

public class Singleton {  
	private static Singleton instance;  
	private Singleton (){}  
	public static synchronized Singleton getInstance() {  
		if (instance == null) {  
			instance = new Singleton();  
		}  
		return instance;  
	}  
}

