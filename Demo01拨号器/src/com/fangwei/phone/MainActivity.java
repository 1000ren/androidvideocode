package com.fangwei.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
   
	private EditText mobileText;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //һ��������
        setContentView(R.layout.main);
        //����������Ӧ�ĵ���¼� �� ���Ź���
        //��ȡ�����ĵ绰����
        mobileText = (EditText) findViewById(R.id.moblie);
        //��ȡ��ť��ID
        Button button = (Button) this.findViewById(R.id.button);
        //����ťʵʩ����
        button.setOnClickListener(new ButtonClickListener()); 
    }

	
	//�ڲ��� ���� ��ť�� -----�̳� view��onClickListener�ļ�������
	private final class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
			//���绰����ת��Ϊ�ַ�����
			String number = mobileText.getText().toString();
			//�½�һ��intent�¼�
			Intent intent = new Intent();
			//��intent�¼�����android�Ĵ�绰�Ķ���
			intent.setAction("android.intent.action.CALL");
			//��intent��������
			intent.setData(Uri.parse("tel:"+number));
			//�������¼�
			startActivity(intent);
			//�����ڲ����Զ�Ϊintent������android.intent.category.DEFAULT		
		}
		
	}
	
}

	
	
	
	
	