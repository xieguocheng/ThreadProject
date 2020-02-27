package com.chapter14.singleton;

import java.net.Socket;
import java.sql.Connection;

/** 
 *
 * @Description:
 * 4、双检锁/双重校验锁（DCL，即 double-checked locking
 * 优点：高效，允许多个线程访问
 * 缺点：可能出现指令重排序时，初始化数据出现空指针异常(发生在初始化顺序时，互相调用顺序空指针)
 * @Author:	XO
 * @CreateDate:  2020年2月6日 下午8:42:41 
 * 
 */
public class DoubleSingleton {
	private volatile static DoubleSingleton singleton;  
	
/*	Connection conn;
	
	Socket socket;
	
	private DoubleSingleton(){
		this.conn;
		this.socket;
	}*/
	
	private DoubleSingleton (){}  
	public static DoubleSingleton getSingleton() {  
		if (singleton == null) {  
			synchronized (DoubleSingleton.class) {  
				if (singleton == null) {  
					singleton = new DoubleSingleton();  
				}  
			}  
		}  
		return singleton;  
	}  

}
