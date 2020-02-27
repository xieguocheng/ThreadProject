package com.chapter08.pool.接口;
/** 
*
* @Description:拒绝策略
* @Author:	XO
* @CreateDate:  2020年2月6日 下午10:40:22 
* 
*/
@FunctionalInterface
public interface DenyPolicy {
	
	void reject(Runnable runnable,ThreadPool threadPool);
	
	//1.直接丢弃任务
	class DiscardDenyPolicy implements DenyPolicy {

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			
			//TODO
			
		}
		
	}
	
	//2.抛出异常
	class AbortDenyPolicy implements DenyPolicy {

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			
			throw new RuntimeException("the runnable" +runnable+"will be abort");
			
		}
		
	}
	
	//3.直接运行任务
	class RunnerDenyPolicy implements DenyPolicy {

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			
			if(!threadPool.isShutdown()){
				runnable.run();
			}
			
		}
		
	}

}
