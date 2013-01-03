package com.fangwei.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.service.FileService;

public class FileServiceTest extends AndroidTestCase{
	//给Log定义一个常量的标签
	private static final String TAG = "FileServiceTest" ;
	
	public void testRead() throws Exception{
		//得到上下文对象
		FileService service = new FileService(getContext());
		
		//测试他的读取的方法----打印出的结果中：如果中文是乱码 不是程序的问题 是模拟器产生的问题
		String result = service.read("nhhh.txt");
		
		//打印log日志
		Log.i(TAG, result);//打印结果 123456
	}
}
