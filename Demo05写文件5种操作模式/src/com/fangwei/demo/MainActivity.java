package com.fangwei.demo;

import com.fangwei.service.FileService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    //
	private EditText nameText;
	private EditText contentText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //��ȡ�����  ��ť
        nameText = (EditText) this.findViewById(R.id.filename);
        contentText = (EditText) this.findViewById(R.id.filecontent);
        Button button = (Button) this.findViewById(R.id.button);
        
        //ʵʩ��ť����
        button.setOnClickListener(new ButtonOnClickListener());
        
	
	}
	
	 private class ButtonOnClickListener implements View.OnClickListener{

		public void onClick(View v) {
			//������������ת��Ϊ�ַ���
			String name = nameText.getText().toString();
			String content = contentText.getText().toString();
			
			//д��ôһ��ҵ���� ���ļ���  ��  �ļ� ���ݽ��д���
			FileService fileService = new FileService (getApplicationContext()) ;
			
			try {
				//˽��ģʽ
//				fileService.save01(name, content);
				//׷��ģʽ
				fileService.save02(name, content);
				//�ɶ�ģʽ
//				fileService.save03(name, content);
				//��дģʽ
//				fileService.save04(name, content);
				//��дģʽ
//				fileService.save04(name, content);
				
				
				
				Toast.makeText(getApplicationContext(), R.string.success, 1).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.fale, 1).show();
			}
			
			
			
		}
		 
	 }
}