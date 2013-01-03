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
        
        //ȡ�ø������
        Map<String,String> params = service.getPreferences();
        //���������ı������
        nameText.setText(params.get("name"));
        ageText.setText(params.get("age"));
        
	}
   
    public void save(View v) {
//����Ҳ�ǻ�ȡ     	Preferences��ʽ Ĭ�� �� MainActvity.xml��Ϊ�����ļ�
//    	this.getPreferences(Context.MODE_PRIVATE);
//		SharedPreferences preferences = context.getSharedPreferences("fangwei", Context.MODE_PRIVATE);

    	//�������תΪString �ַ�����
    	String name = nameText.getText().toString();
    	String age = ageText.getText().toString();
    
    	//�����ֱ���ʹ�õ�service��
//    	service = new PreferencesService(this);
    	service.save(name,Integer.valueOf(age));
    	//��ʾ������Ϣ
    	Toast.makeText(getApplicationContext(), R.string.success, 1).show();
    
    }
    
}