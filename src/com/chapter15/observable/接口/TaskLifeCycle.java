package com.chapter15.observable.接口;
/** 
*
* @Description:任务的生命周期
* @Author:	XO
* @CreateDate:  2020年2月23日 下午1:04:23 
* 
*/
public interface TaskLifeCycle<T> {
    void onStart(Thread thread);
    void onRunning(Thread thread);
    void onFinish(Thread thread,T result);//观察者模式，获取返回值
    void onError(Thread thread,Exception e);
    
    /**
     * 内部实现类
     * @author asus
     *
     * @param <T>
     */
    class EmptyLifeCycle<T> implements TaskLifeCycle<T>{

        @Override
        public void onStart(Thread thread) {
            //留给自己实现
        }

        @Override
        public void onRunning(Thread thread) {
        	//留给自己实现
        }

        @Override
        public void onFinish(Thread thread, T result) {
        	//留给自己实现
        }

        @Override
        public void onError(Thread thread, Exception e) {
        	//留给自己实现
        }
    }
}


