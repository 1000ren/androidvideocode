package com.fangwei.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fangwei.utils.FormFile;
import com.fangwei.utils.SocketHttpRequester;



public class HttpService {

	public static boolean save(String title, String length, File uploadFile) {
		String path = "http://192.168.1.102:8080/http/ManageServlet";
													
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		params.put("timelength", length);
		FormFile formFile = new FormFile(uploadFile, "videofile", "image/gif");
		try {
			return SocketHttpRequester.post(path, params, formFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ͨ��HttpClient����Post����
	 * @param path ����·��
	 * @param params �������
	 * @param encoding ����
	 * @return �����Ƿ�ɹ�
	 */
	private static boolean sendHttpClientPOSTRequest(String path, Map<String, String> params, 
			String encoding) throws Exception{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();//����������
		if(params!=null && !params.isEmpty()){
			for(Map.Entry<String, String> entry : params.entrySet()){
				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, encoding);
		HttpPost httpPost = new HttpPost(path);
		httpPost.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		if(response.getStatusLine().getStatusCode() == 200){
			return true;
		}
		return false;
	}
	
	
	/**
	 * ��������
	 * @param title ����
	 * @param length ʱ��
	 * @return
	 */
	public static boolean save(String title, String length) {
		String path = "http://192.168.1.102:8080/http/ManageServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		params.put("timelength", length);
		try {
			return sendHttpClientPOSTRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
