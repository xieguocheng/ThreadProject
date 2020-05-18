package com.chapter24.perMessage;
/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月29日 下午7:52:15 
 * 
 */
public class Test {
	public static void main(String[] args) {
		MessageHandler messageHandler = new MessageHandler();
		for (int i = 0; i < 5; i++){
			messageHandler.request(new Message("信息：" + i));
		}
	}
}
