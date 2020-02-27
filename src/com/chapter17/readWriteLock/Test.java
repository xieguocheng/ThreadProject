package com.chapter17.readWriteLock;
/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月23日 下午8:08:28 
 * 
 */
public class Test {
	
	private final static String text = "Thisistheexampleforreadwritelock";
	
	
	public static void main(String[] args) {
		
		
		//定义共享数据
		final ShareData shareData = new ShareData(50);

		//2个线程负责数据的写
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				for (int index = 0; index < text.length(); index++) {
					try{
						char c = text.charAt(index);
						shareData.write(c);
						System.out.println(Thread.currentThread()+" write "+c);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		//10个线程负责数据的读
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				while (true) {
					try{
						System.out.println(Thread.currentThread()+" read "+new
								String(shareData.read()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
