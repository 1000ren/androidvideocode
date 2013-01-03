package com.fangwei.MulActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	    }
	    
	    public void openActivity(View v){
	    	//打开新的Activity
	    	//激活组件,显示意图:明确指定了组件名称的意图叫显示意图
	    	Intent intent = new Intent(this, OtherActivity.class);
	    	//关于指定组件名称，还有很多写法
	    	//1> intent.setClass(this, OtherActivity.class);//指定要激活的组件名称
	    	//2> intent.setClassName(this, "cn.itcast.activitys.OtherActivity");
	    	//3> intent.setComponent(new ComponentName(this, OtherActivity.class));
	    	/*
	    	 第一种 参数的传递
	    	intent.putExtra("company", "武汉锐科");
	    	intent.putExtra("age", 5);
	    	*/
	    	Bundle bundle = new Bundle();
	    	//
	    	//另一种传递参数的方法 批量添加的方法 ----使用bundle
	    	bundle.putString("company", "方威的公司");
	    	bundle.putInt("age", 11);
	    	intent.putExtras(bundle);
	    	
	    	//startActivity(intent);
	    	startActivityForResult(intent, 100);
	    }

	    //使用startactivityForResult来激活
	    //获取返回码
	    //需要重写activity里面的方法：
	    //此方法不由程序员来调用。直接由系统来调用

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			String result = data.getStringExtra("result");
			Toast.makeText(getApplicationContext(),result, 1).show();
			
			super.onActivityResult(requestCode, resultCode, data);
		}
	    
	    
	}