package com.fangwei.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;

public class FileService {
	//上下文
	private Context context ;
	
	public FileService(Context context) {
		this.context = context ;
	}
	
	/**
	 * 保存文件  私有模式
	 * @param filename 文件名
	 * @param content 文件内容
	 * @throws Exception
	 */
	public void save01(String filename,String content) throws Exception {
		//对文件名进行操作 ----- 调用Android的context上下文 的openFileOutput 创建文件对象
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//对文件内容进行操作 
		outStream.write(content.getBytes());
		//关闭流
		outStream.close();
	}
	/**
	 * 保存文件  追加模式
	 * @param filename 文件名
	 * @param content 文件内容
	 * @throws Exception
	 */
	public void save02(String filename,String content) throws Exception {
		//对文件名进行操作 ----- 调用Android的context上下文 的openFileOutput 创建文件对象
		//追加模式 ： 不覆盖 ， 内容 添加到前内容尾
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_APPEND);
		//对文件内容进行操作 
		outStream.write(content.getBytes());
		//关闭流
		outStream.close();
	}
	/**
	 * 保存文件  只读模式
	 * @param filename 文件名
	 * @param content 文件内容
	 * @throws Exception
	 */
	public void save03(String filename,String content) throws Exception {
		//对文件名进行操作 ----- 调用Android的context上下文 的openFileOutput 创建文件对象
		//只读 模式 ：只可供其他应用 读
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_READABLE);
		//对文件内容进行操作 
		outStream.write(content.getBytes());
		//关闭流
		outStream.close();
	}
	/**
	 * 保存文件  只写模式
	 * @param filename 文件名
	 * @param content 文件内容
	 * @throws Exception
	 */
	public void save04(String filename,String content) throws Exception {
		//对文件名进行操作 ----- 调用Android的context上下文 的openFileOutput 创建文件对象
		// 只写模式：只可以供其他应用写入
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_WRITEABLE);
		//对文件内容进行操作 
		outStream.write(content.getBytes());
		//关闭流
		outStream.close();
	}
	/**
	 * 保存文件  读写模式
	 * @param filename 文件名
	 * @param content 文件内容
	 * @throws Exception
	 */
	public void save05(String filename,String content) throws Exception {
		//对文件名进行操作 ----- 调用Android的context上下文 的openFileOutput 创建文件对象
		// 读写模式 ：可供其他应用读写
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
		//对文件内容进行操作 
		outStream.write(content.getBytes());
		//关闭流
		outStream.close();
	}
	
	
	/**
	 * 读取文件的内容
	 * @param filename 文件名称
	 * @return  文件内容
	 * @throws Exception
	 */
	 
	public String read(String filename) throws Exception {
		//往内存写数据
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		FileInputStream inStream = context.openFileInput(filename);
		byte [] buffer = new byte[1024];
		int len = 0 ;
		//判定流中是否有数据
		while  ((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		outStream.close();
		inStream.close();
		//存放在 内存中的数据 属于 字节 数据
		byte [] data = outStream.toByteArray();
		//把UTF-8转换为字符串
		return new String(data);
	}
}
