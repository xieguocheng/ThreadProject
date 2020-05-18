package com.chapter22.balking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月28日 下午8:06:18 
 * 
 */

/*
   22.2 Balking模式之文档编辑
   多个线程监控某个共享变量，A线程监控到共享变量发生变化后即立即触发某个动作，但是
   此时发现另外一个线程B已经针对该变量的变化开始了行动，因此A便放弃了准备开始的工作
   ，我们把这种线程间交互称作Balking（犹豫）设计模式

       ——简而言之，就是当你去获取临界区资源时，发现正有人在使用着，那么你没有阻塞
           而是放弃了去获取这部分的资源。你认定，别的线程已经开始了对这部分资源的
           修改操作，所以你无需操作。
 */
public class Document{
	private boolean changed = false;

	private List<String> content = new ArrayList<>();

	private final FileWriter writer;

	private static AutoSaveThread autoSaveThread;

	private Document(String documentPath, String documentName)
			throws IOException {
		this.writer = new FileWriter(new File(documentPath,documentName),true);
	}

	public static Document create(String documentPath, String documentName)
			throws IOException{
		Document document = new Document(documentPath,documentName);
		autoSaveThread = new AutoSaveThread(document);
		autoSaveThread.start();
		return document;
	}

	public void edit(String content) {
		synchronized (this) {
			this.content.add(content);
			this.changed=true;
		}
	}

	public void close() throws IOException{
		autoSaveThread.interrupt();
		writer.close();
	}

	public void save() throws IOException{
		synchronized (this) {
			if (!changed) {
				return;
			}

			System.out.println(Thread.currentThread()+" execute the save action");

			for (String cacheLine : content) {
				this.writer.write(cacheLine);
				this.writer.write("\r\n");
			}
			this.writer.flush();
			this.changed=false;
			this.content.clear();
		}
	}
}
