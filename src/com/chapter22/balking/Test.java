package com.chapter22.balking;
/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月28日 下午8:40:27 
 * 
 */
public class Test {
	public static void main(String[] args) {
		new DocumentEditThread("E:\\","balking.txt").start();
	}
}
