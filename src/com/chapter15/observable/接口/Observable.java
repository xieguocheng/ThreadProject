package com.chapter15.observable.接口;
/** 
*
* @Description:观察者对象
* @Author:	XO
* @CreateDate:  2020年2月23日 下午1:03:39 
* 
*/
public interface Observable {
	/**
	 * 枚举类
	 * @author asus
	 *
	 */
    enum Cycle{
        STARTED, RUNNING, DONE, ERROR
    }
    
    Cycle getCycle();
    
    
    //主要是为了屏蔽Thread的其他方法（子类实现了thread对象可以不实现这个方法，因为thread含有这个方法）
    void start();
    //主要是为了屏蔽Thread的其他方法（子类实现了thread对象可以不实现这个方法，因为thread含有这个方法）
    void interrupt();
}
