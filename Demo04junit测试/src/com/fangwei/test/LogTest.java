package com.fangwei.test;

import android.test.AndroidTestCase;
import android.util.Log;

public class LogTest extends AndroidTestCase {
	private static final String TAG =  "LogTest";

	public  void testLog1() throws Exception {
		//��Ϊ��־�ı�־
		Log.i(TAG,"fangwei.com");
		//��LogCat�²鿴 
	}
	
	public void testLog2() {
		//���������Ϣ
		Log.e(TAG, "error message");
	}
	
	
	public  void testLog3() {
		//Ĭ�ϵ���־��־λ System.out
		//�����ǣ�info
		System.out.println("www.sohu.com");
	}
	public  void testLog4() {
		//�����Ǿ���
		System.err.println("www.sina.com");
	}
	
	
 
}
