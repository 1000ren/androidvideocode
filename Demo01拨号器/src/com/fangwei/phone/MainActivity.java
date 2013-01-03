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
      //一：画界面
        setContentView(R.layout.main);
        //二：生成相应的点击事件 和 拨号功能
        //获取输入框的电话号码
        mobileText = (EditText) findViewById(R.id.moblie);
        //获取按钮的ID
        Button button = (Button) this.findViewById(R.id.button);
        //给按钮实施监听
        button.setOnClickListener(new ButtonClickListener()); 
    }

	
	//内部类 监听 按钮的 -----继承 view的onClickListener的监听方法
	private final class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
			//将电话号码转换为字符串型
			String number = mobileText.getText().toString();
			//新建一个intent事件
			Intent intent = new Intent();
			//给intent事件赋予android的打电话的动作
			intent.setAction("android.intent.action.CALL");
			//给intent设置数据
			intent.setData(Uri.parse("tel:"+number));
			//启动此事件
			startActivity(intent);
			//方法内部会自动为intent添加类别：android.intent.category.DEFAULT		
		}
		
	}
	
}

	
	
	
	
	