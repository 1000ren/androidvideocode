package com.fangwei.service;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	//引入上下文对象
	private Context context; 
	public PreferencesService(Context context) {
		this.context = context ;
	}
	
	/**
	 * 保存参数
	 * @param name 姓名
	 * @param age
	 */
	public void save(String name,Integer age) {
		//name 文件名称 千万不要有后缀名.xml   /  操作模式  ---选择私有的
		SharedPreferences preferences = 
				context.getSharedPreferences("fangwei", Context.MODE_PRIVATE);
		//得到编辑器对象
		Editor editor = preferences.edit();
		//以下是文件保存到了内存中 但并没有保存到文件中
		editor.putString("name", name);
		editor.putInt("age", age);
		//把内存中数据 提交到文件中
		editor.commit();
		
	}
	
	/**
	 * 获取各项配置参数
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
