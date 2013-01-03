package com.fangwei.service;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	//���������Ķ���
	private Context context; 
	public PreferencesService(Context context) {
		this.context = context ;
	}
	
	/**
	 * �������
	 * @param name ����
	 * @param age
	 */
	public void save(String name,Integer age) {
		//name �ļ����� ǧ��Ҫ�к�׺��.xml   /  ����ģʽ  ---ѡ��˽�е�
		SharedPreferences preferences = 
				context.getSharedPreferences("fangwei", Context.MODE_PRIVATE);
		//�õ��༭������
		Editor editor = preferences.edit();
		//�������ļ����浽���ڴ��� ����û�б��浽�ļ���
		editor.putString("name", name);
		editor.putInt("age", age);
		//���ڴ������� �ύ���ļ���
		editor.commit();
		
	}
	
	/**
	 * ��ȡ�������ò���
	 * @return
	 */
	public Map<String,String>  getPreferences() {
		Map<String,String> params = new HashMap<String,String>();
		SharedPreferences preferences = 
				context.getSharedPreferences("fangwei", Context.MODE_PRIVATE);
		params.put("name", preferences.getString("name", ""));
		params.put("age", String.valueOf(preferences.getInt("age", 0)));
		return params;
	}
}
