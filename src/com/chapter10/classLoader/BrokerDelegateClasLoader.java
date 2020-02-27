package com.chapter10.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 
*
* @Description:
* @Author:	XO
* @CreateDate:  2020年2月22日 下午10:48:07 
* 
*/
public class BrokerDelegateClasLoader extends ClassLoader {
	
	
	private final static Path DEFAULT_CLASS_DIR = Paths.get("E:",  "classloader1");

	   private final Path classDir;

	   public BrokerDelegateClasLoader()
	   {
	          super();
	          this.classDir = DEFAULT_CLASS_DIR;
	   }
	   
	   public BrokerDelegateClasLoader(String classDir)
	   {
	          super();
	          this.classDir = Paths.get(classDir);
	   }

	   public BrokerDelegateClasLoader(String classDir,ClassLoader parent)
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
	   /*
	    * (non-Javadoc)
	    * @see java.lang.ClassLoader#loadClass(java.lang.String, boolean)
	    * 重写loadclass可以破坏夫委托机制。
	    */
		protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		   synchronized (getClassLoadingLock(name)) {
			   
			   Class<?> klass=findLoadedClass(name);
			   
			   if(klass==null){
				   if(name.startsWith("java.")||name.startsWith("javax")){
					  try{
						  klass=getSystemClassLoader().loadClass(name);
					  }catch (Exception e) {
						//ignore
					}
				   }else{
					   
					   try{
							  klass = this.findClass(name);
						  }catch (Exception e) {
							//ignore
						}
					   if(klass==null){
						   if(getParent()!=null){
							   klass=getParent().loadClass(name);
						   }else{
							   klass=getSystemClassLoader().loadClass(name);
						   }
					   }
					   
				   }
			   }
			   
			   if(null==klass){
				   throw new ClassNotFoundException("the class"+name+"not found.");
			   }
			   
			   if(resolve){
				   resolveClass(klass);
			   }
			   
			   return klass;
			   
			
		}
		   
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
