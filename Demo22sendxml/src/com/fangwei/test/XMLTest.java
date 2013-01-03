package com.fangwei.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.test.AndroidTestCase;

import com.fangwei.utils.StreamTool;

public class XMLTest extends AndroidTestCase{
	public void testSendXML() throws Exception{
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		byte[] data = StreamTool.read(inStream);
		String path = "http://192.168.1.102:8080/sendxml/XMLServlet";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		//注意这里的编码集
		conn.setRequestProperty("Content-Type", "text/xml; charset=GBK");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.getOutputStream().write(data);
		if(conn.getResponseCode() == 200){
			System.out.println("发送成功");
		}else{
			System.out.println("发送失败");
		}
	}
}
