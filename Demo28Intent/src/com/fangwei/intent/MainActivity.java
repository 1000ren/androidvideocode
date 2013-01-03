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
    	//在没有设置data参数的情况下，Intent对象中的action和category必须出现在Intent-Filter中，
    	//如果没有出现，就无法与Intent-Filter匹配，
    	//只要Intent对象中的action和category出现在Intent-Filter中,就必然可以跟Intent-Filter匹配
    	Intent intent = new Intent();
    	intent.setAction("cn.fangwei.laoli");
    	intent.addCategory("cn.fangwei.category.zhangxx");
    	intent.setDataAndType(Uri.parse("fangwei://www.fangwei.cn/liming"), "image/gif");
    	/*
    	intent.setData(Uri.parse("itcast://www.itcast.cn/liming"));
    	intent.setType("image/gif");
    	*/
    	
    	startActivity(intent);//内部会隐式注册一个类别：android.intent.category.DEFAULT
    }
    
}