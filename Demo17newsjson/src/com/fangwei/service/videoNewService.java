package com.fangwei.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fangwei.domain.News;
import com.fangwei.utils.StreamTool;


public class videoNewService {
	/**
	 * ��ȡ���µ���Ƶ��ѯ
	 * 
	 */
	public static  List<News> getJSONLastNews() throws Exception{
		//
		String path  = "http://192.168.1.102:8080/videonewsjson/ListServlet?format=json";
											// /videonewsjson/ListServlet?format=json
		
		URL url =new URL(path);
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		//�ж���վ�ķ�����
		if(conn.getResponseCode() == 200){
			InputStream inStream = conn.getInputStream();
			return parseJSON(inStream);
		}
		return null;
	}

	/**
	 * ����JSON����
	 * @param inStream
	 * @return
	 */
	private static List<News> parseJSON(InputStream inStream) throws Exception {
		List<News> newses = new ArrayList<News>();
		byte[] data = StreamTool.read(inStream);
		String json = new String(data);
		//
		JSONArray array = new JSONArray(json);
		for(int i = 0;i<array.length();i++){
			JSONObject jsonObject = array.getJSONObject(i);
			News news = new News(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getInt("timelength"));
			newses.add(news);
		}
		
		return newses;
	}

	
}
