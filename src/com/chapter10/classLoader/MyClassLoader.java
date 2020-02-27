package com.chapter10.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 
*
* @Description:
* 编译：javac -encoding utf-8 ClassLoaderHello.java
* @Author:	XO
* @CreateDate:  2020年2月22日 下午7:48:33 
* 
*/
public class MyClassLoader extends ClassLoader{
	
	private final static Path DEFAULT_CLASS_DIR = Paths.get("E:",  "classloader1");

	   private final Path classDir;

	   public MyClassLoader()
	   {
	          super();
	          this.classDir = DEFAULT_CLASS_DIR;

	   }
	   public MyClassLoader(String classDir)
	   {
	          super();
	          this.classDir = Paths.get(classDir);
	   }

	   public MyClassLoader(String classDir,ClassLoader parent)
	   {
	          super(parent);
	          this.classDir = Paths.get(classDir);
	   }
	   
	   @Override
	   /*
	    * (non-Javadoc)
	    * @see java.lang.ClassLoader#findClass(java.lang.String)
	    * 必须要重写这个类
	    */
	   protected Class<?> findClass(String name) throws ClassNotFoundException
	   {
	          byte[] classBytes = this.readClassBytes(name);
	          if(null == classBytes || classBytes.length == 0)
	          {
	                 throw new ClassNotFoundException("can not load the class ");
	          }
	          return this.defineClass(name, classBytes, 0,classBytes.length);
	   }

	   @Override
	   public String toString()
	   {
	          return "My ClassLoader";
	   }

	   private byte[] readClassBytes(String name)throws ClassNotFoundException
	   {
	          String classPath = name.replace(".", "/");
	          Path classFullPath =  classDir.resolve(Paths.get(classPath+".class"));
	          if(!classFullPath.toFile().exists())
	          {
	                 throw new ClassNotFoundException("The Class "+name+" not  found");
	          }
	          try(ByteArrayOutputStream baos = new ByteArrayOutputStream())
	          {
	                 Files.copy(classFullPath, baos);
	                 return baos.toByteArray();
	          }
	          catch(IOException e)
	          {
	                 throw new ClassNotFoundException("Load the class "+  name +"  occur error.",e);
	          }
	   }
	   
	 

}
