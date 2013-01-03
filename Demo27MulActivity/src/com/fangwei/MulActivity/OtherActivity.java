package com.fangwei.MulActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		
		TextView textView = (TextView) this.findViewById(R.id.textView);
		
		Intent intent = getIntent();//��ȡ���ڼ���������ͼ����
		//��һ�� �������ݵ� ���յķ���
	/*	String company = intent.getStringExtra("company");
		int age = intent.getIntExtra("age", 0);
	*/
		//�ڶ��� ���� ���� ���յķ���
		Bundle bundle = intent.getExtras();
		String company = bundle.getString("company");
		int age = bundle.getInt("age");
		
		textView.setText("��˾���ƣ�"+ company+ ",���ޣ�"+ age);
	}

	//�ر�activity 
	public void closeActivity(View v){
		Intent intent = new Intent();
		intent.putExtra("result", "������1000ren�Ĺ��£�����ʡ��2000����");
		setResult(20, intent);//���÷�������
		finish();//�ر�Activity(�رմ���)
	}
}
