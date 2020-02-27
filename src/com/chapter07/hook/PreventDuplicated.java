package com.chapter07.hook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/** 
 *
 * @Description:
 * @Author:	XO
 * @CreateDate:  2020年2月20日 下午9:06:35 
 * 
 */
class PreventDuplicated {
	//C:\Users\asus\Desktop
	private final static String LOCK_PATH = "C:\\Users\\asus\\Desktop\\test";
	private final static String LOCK_FILE = ".lock";
	private final static String PERMISSIONS = "rw-------";

	private static Path getLockFile(){
		return Paths.get(LOCK_PATH,LOCK_FILE);
	}

	private static void checkRunning(){
		Path path = getLockFile();

		if(path.toFile().exists()){
			throw new RuntimeException("The program already running...");
		}

		/*
	            很尴尬一点，这个地方貌似是针对Linux的写法，我这个地方出现了问题，
	            暂时先将这个问题放一下吧。。。
		 */
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);

		try {
			Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
    7.2.3 Hook线程应用场景及注意事项
        1.Hook线程只有在收到退出信号的时候会被执行，如果kill使用了-9，那么
            Hook线程不会执行，因此lock文件也不会被清理
        2.Hook线程中也可以执行一些资源的释放工作，如关闭文件句柄、socket链接
            、数据库connection等
	 */
	public static void main(String[] args){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("The program received kill SIGNAL.");
				getLockFile().toFile().delete();
			}
		});

		checkRunning();

		while (true) {
			try{
				TimeUnit.MILLISECONDS.sleep(1);
				System.out.println("program is running...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
