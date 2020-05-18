package com.chapter26.workerThread;

/** 
*
* @Description:床送带
* @Author:	XO
* @CreateDate:  2020年2月29日 下午8:26:50 
* 
*/

public class ProductionChannel{
	
    private final static int MAX_PROD = 100;
    
    private final Production[] productionQueue;

    private int tail;   //队列尾
    private int head;   //队列头
    private volatile int total;  //当前流水有多少个待加工的产品

    private final Worker[] workers;

    public ProductionChannel(int workSize) {
        this.workers=new Worker[workSize];
        this.productionQueue=new Production[MAX_PROD];

        //开启worksize个工作人员(Thread)线程
        for (int i = 0; i < workSize; i++) {
            workers[i]=new Worker("Worker-"+i,this);
            workers[i].start();
        }
    }

    /**
     * 添加
     * @param production
     */
    public void offerProduction(Production production){
        synchronized (this) {
            while (total >= productionQueue.length) {
                try{
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            productionQueue[tail]=production;
            tail=(tail+1)%productionQueue.length;
            total++;
            this.notifyAll();
        }
    }

    /**
     * 获取
     * @return
     */
    public Production takeProduction(){
        synchronized (this) {
            while (total <= 0) {
                try{
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Production production = productionQueue[head];//？？？？
        head = (head+1)%productionQueue.length;
        total--;
        this.notifyAll();;
        return production;
    }
}
