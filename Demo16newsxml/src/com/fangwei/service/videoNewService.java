package com.fangwei.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.fangwei.domain.News.News;

public class videoNewService {
	/**
	 * 获取最新的视频咨询
	 * 
	 */
	public static  List<News> getLastNews() throws Exception{
		//
//		String path1 = "http://192.168.1.102:8080/vedionews/ListServlet";
		String path = "http://192.168.1.102:8080/videonews/ListServlet";
		URL url =new URL(path);
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		//判断网站的返回码
		if(conn.getResponseCode() == 200){
			InputStream inStream = conn.getInputStream();
			return parseXMl(inStream);
		}
		return null;
	}

	/**
	 * 
	 <videonews>
	<news id="35">
	<title>喜羊羊与灰太狼的全集</title>
	<timelength>90</timelength>
	</news>
	<news id="12">
	<title>老张与灰太狼</title>
	<timelength>20</timelength>
	</news>
	<news id="56">
	<title>老方与LILI</title>
	<timelength>30</timelength>
	</news>
	</videonews>
	 */
	private static List<News> parseXMl(InputStream inStream) throws Exception {
		
		List<News> newses = new ArrayList<News>();
		News news = null ;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream,"UTF-8");
		int event = parser.getEventType();
		while(event != XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_TAG:
				if("news".equals(parser.getName())){
					int id = new Integer(parser.getAttributeValue(0));
					news = new News();
					news.setId(id);
				}else if("title".equals(parser.getName())){
					news.setTitle(parser.nextText());
				}else if("timelength".equals(parser.getName())){
					news.setTimelength(new Integer(parser.next()));
				}
				break;
			
			case XmlPullParser.END_TAG:
				if("news".equals(parser.getName())){
					newses.add(news);
					news = null;
				}
				break;
			}
			event = parser.next();
		}
		return newses;

	}
}
