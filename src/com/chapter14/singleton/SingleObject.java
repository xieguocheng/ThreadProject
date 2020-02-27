package com.chapter14.singleton;
/** 
 *
 * @Description:1、饿汉式，线程安全
 * @Author:	XO
 * @CreateDate:  2020年2月6日 下午8:38:09 
 * 
 */
public class SingleObject {

	//创建 SingleObject 的一个对象
	private static SingleObject instance = new SingleObject();

	//让构造函数为 private，这样该类就不会被实例化
	private SingleObject(){}

	//获取唯一可用的对象
	public static SingleObject getInstance(){
		return instance;
	}

	public void showMessage(){
		System.out.println("Hello World!");
	}



}
