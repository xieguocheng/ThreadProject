package com.chapter14.singleton;
/** 
*
* @Description:
* 使用静态内部类进行初始化，holder方式是单例设计最好的设计之一。用的最广泛
* @Author:	XO
* @CreateDate:  2020年2月20日 下午9:38:40 
* 
*/
public class HolderSingleton {
	
	private byte[] data=new byte[1024];
	
	private HolderSingleton(){
		
	}

	//静态内部类
	private static class Holder{
		private static HolderSingleton instance =new HolderSingleton();
	}
	
	public static HolderSingleton getInstance(){
		return Holder.instance;
	}
}
