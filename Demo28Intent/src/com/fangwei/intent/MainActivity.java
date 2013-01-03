package com.fangwei.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void openActivity(View v){
    	//��û������data����������£�Intent�����е�action��category���������Intent-Filter�У�
    	//���û�г��֣����޷���Intent-Filterƥ�䣬
    	//ֻҪIntent�����е�action��category������Intent-Filter��,�ͱ�Ȼ���Ը�Intent-Filterƥ��
    	Intent intent = new Intent();
    	intent.setAction("cn.fangwei.laoli");
    	intent.addCategory("cn.fangwei.category.zhangxx");
    	intent.setDataAndType(Uri.parse("fangwei://www.fangwei.cn/liming"), "image/gif");
    	/*
    	intent.setData(Uri.parse("itcast://www.itcast.cn/liming"));
    	intent.setType("image/gif");
    	*/
    	
    	startActivity(intent);//�ڲ�����ʽע��һ�����android.intent.category.DEFAULT
    }
    
}