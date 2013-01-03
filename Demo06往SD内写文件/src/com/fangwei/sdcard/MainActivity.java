package com.fangwei.sdcard;

import com.fangwei.service.FileSdService;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
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
			FileSdService service = new FileSdService(getApplicationContext()) ;
			
			try {
				
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				service.saveToSdCard(name, content);
				Toast.makeText(getApplicationContext(), R.string.success, 1).show();
				}else {
					Toast.makeText(getApplicationContext(), R.string.sdcardnoexsit, 1).show();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.fale, 1).show();
			}		
		}
	 }
}