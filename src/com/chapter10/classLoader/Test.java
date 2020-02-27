package com.chapter10.classLoader;

import java.lang.reflect.Method;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月22日 下午7:57:26 
 * 
 */
public class Test {

	public static void main(String[] args) {
		MyClassLoader classLoader = new MyClassLoader();
		Class<?> aClass  =null;
		try {
			aClass =  classLoader.loadClass("com.chapter10.classLoader.ClassLoaderHello");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(aClass.getClassLoader());
		try {
			Object helloWorld = aClass.newInstance();
			System.out.println(helloWorld);
			Method welcomeMethod = aClass.getMethod("welcome");
			String result = (String) welcomeMethod.invoke(helloWorld);
			System.out.println(result);

		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
