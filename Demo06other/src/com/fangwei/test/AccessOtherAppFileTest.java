package com.fangwei.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.utils.StreamTool;

public class AccessOtherAppFileTest extends AndroidTestCase{
	//新建一个 常量标签
	private static final String TAG ="AccessOtherAppFileTest";
	
	
	//测试
	public void testAccessprivateFile() throws Exception{
		String path = "/data/data/com.fangwei.demo/files/gg.txt";
		File file = new File (path);
		InputStream inStream = new FileInputStream(file) ;		
		byte[] data = StreamTool.read(inStream);	
		String content = new String(data);
		Log.i(TAG,content);
			
	
		
		
	}
	
}
