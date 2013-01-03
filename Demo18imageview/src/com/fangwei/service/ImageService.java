package com.fangwei.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fangwei.utils.StreamTool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public class ImageService {	
	/**
	 * 获取图片
	 * @param path 图片路径
	 * @return
	 */
	public static  Bitmap getImage(String path) throws Exception  {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			InputStream inStream = conn.getInputStream();
			
			//1：使用工具类的方法
			
			byte[] data = StreamTool.read(inStream);
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			return bitmap;
			
			
			//2：使用Android自带的流解析
//			Bitmap bitmap = BitmapFactory.decodeStream(inStream);
//			return bitmap;
//				
		}
		return null;
	}
}
