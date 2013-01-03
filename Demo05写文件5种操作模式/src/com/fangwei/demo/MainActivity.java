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
        //获取输入框  按钮
        nameText = (EditText) this.findViewById(R.id.filename);
        contentText = (EditText) this.findViewById(R.id.filecontent);
        Button button = (Button) this.findViewById(R.id.button);
        
        //实施按钮监听
        button.setOnClickListener(new ButtonOnClickListener());
        
	
	}
	
	 private class ButtonOnClickListener implements View.OnClickListener{

		public void onClick(View v) {
			//将输入框的文字转换为字符串
			String name = nameText.getText().toString();
			String content = contentText.getText().toString();
			
			//写这么一个业务类 对文件名  和  文件 内容进行处理
			FileService fileService = new FileService (getApplicationContext()) ;
			
			try {
				//私有模式
//				fileService.save01(name, content);
				//追加模式
				fileService.save02(name, content);
				//可读模式
//				fileService.save03(name, content);
				//可写模式
//				fileService.save04(name, content);
				//读写模式
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