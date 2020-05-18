package com.chapter26.workerThread.接口;
/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月29日 下午8:24:47 
* 
*/
public abstract class InstructionBook{
	//加工模板说明书
    public final void create(){
        this.firstProcess();
        this.sedoncProcess();
    }

    protected abstract void firstProcess();
    protected abstract void sedoncProcess();
}
