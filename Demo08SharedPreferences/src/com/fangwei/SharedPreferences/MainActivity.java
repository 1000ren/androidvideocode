package com.fangwei.SharedPreferences;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fangwei.service.PreferencesService;

public class MainActivity extends Activity {
	private EditText nameText;
	private EditText ageText;
	private PreferencesService service;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nameText = (EditText) this.findViewById(R.id.name);
        ageText = (EditText) this.findViewById(R.id.age);
        service = new PreferencesService(this);
        
        //取得各项参数
        Map<String,String> params = service.getPreferences();
        //填充给两个文本输入框
        nameText.setText(params.get("name"));
        ageText.setText(params.get("age"));
        
	}
   
    public void save(View v) {
//以下也是获取     	Preferences方式 默认 会 MainActvity.xml作为保存文件
//    	this.getPreferences(Context.MODE_PRIVATE);
//		SharedPreferences preferences = context.getSharedPreferences("fangwei", Context.MODE_PRIVATE);

    	//将输入框转为String 字符串型
    	String name = nameText.getText().toString();
    	String age = ageText.getText().toString();
    
    	//这里又必须使用到service层
//    	service = new PreferencesService(this);
    	service.save(name,Integer.valueOf(age));
    	//显示出此信息
    	Toast.makeText(getApplicationContext(), R.string.success, 1).show();
    
    }
    
}