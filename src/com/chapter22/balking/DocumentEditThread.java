package com.chapter22.balking;

import java.io.IOException;
import java.util.Scanner;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月28日 下午8:39:11 
* 
*/
public class DocumentEditThread extends Thread{
	
    private final String documentPath;
    private final String documentName;
    private final Scanner scanner = new Scanner(System.in);
 
    public DocumentEditThread(String documentPath, String documentName) {
        super("DocumentEditThread");
        this.documentPath = documentPath;
        this.documentName = documentName;
    }
 
    @Override
    public void run() {
        int times = 0;
 
        try{
            Document document = Document.create(documentPath,documentName);
            while (true) {
                String text = scanner.next();
                if("quit".equals(text)){
                    document.close();
                    break;
                }
                document.edit(text);
                if (times == 5) {
                	////调用save实现balking
                    document.save();
                    times=0;
                }
                times++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
