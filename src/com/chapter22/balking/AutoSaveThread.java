package com.chapter22.balking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月28日 下午8:29:15 
* 
*/
public class AutoSaveThread extends Thread{
	
    private final Document document;
 
    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }
 
    @Override
    public void run() {
        while (true) {
            try{
            	//调用save实现balking
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException|IOException e) {
                break;
            }
        }
    }
}
 
