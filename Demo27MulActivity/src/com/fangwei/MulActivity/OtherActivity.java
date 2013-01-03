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
		
		Intent intent = getIntent();//获取用于激活它的意图对象
		//第一个 参数传递的 接收的方法
	/*	String company = intent.getStringExtra("company");
		int age = intent.getIntExtra("age", 0);
	*/
		//第二种 参数 传递 接收的方法
		Bundle bundle = intent.getExtras();
		String company = bundle.getString("company");
		int age = bundle.getInt("age");
		
		textView.setText("公司名称："+ company+ ",年限："+ age);
	}

	//关闭activity 
	public void closeActivity(View v){
		Intent intent = new Intent();
		intent.putExtra("result", "方威与1000ren的故事，后面省略2000个字");
		setResult(20, intent);//设置返回数据
		finish();//关闭Activity(关闭窗口)
	}
}
