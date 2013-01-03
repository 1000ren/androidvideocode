package com.fangwei.test;

import android.test.AndroidTestCase;
import android.util.Log;

public class LogTest extends AndroidTestCase {
	private static final String TAG =  "LogTest";

	public  void testLog1() throws Exception {
		//作为日志的标志
		Log.i(TAG,"fangwei.com");
		//在LogCat下查看 
	}
	
	public void testLog2() {
		//输出错误信息
		Log.e(TAG, "error message");
	}
	
	
	public  void testLog3() {
		//默认的日志标志位 System.out
		//级别是：info
		System.out.println("www.sohu.com");
	}
	public  void testLog4() {
		//级别是警告
		System.err.println("www.sina.com");
	}
	
	
 
}
