package com.chapter26.workerThread;

import com.chapter26.workerThread.接口.InstructionBook;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月29日 下午8:24:10 
* 
*/
public class Production extends InstructionBook{

    private final int prodID;

    public Production(int prodID) {
        this.prodID = prodID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the "+prodID+" first process");
    }

    @Override
    protected void sedoncProcess() {
        System.out.println("execute the "+prodID+" second process");
    }
}
