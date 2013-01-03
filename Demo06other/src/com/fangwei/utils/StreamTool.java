package com.fangwei.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.util.ByteArrayBuffer;
import org.xml.sax.InputSource;

public class StreamTool {
	
	/**
	 * 从流中读取数据
	 * @param inStream
	 * @return 
	 * @throws Exception
	 */
	public static byte[] read(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte []buffer = new byte[1024];
		int len = 0 ;
		while ((len = inStream.read(buffer))!=-1){
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();	
	}
	
	
	
	
}
