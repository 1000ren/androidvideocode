package com.fangwei.newmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fangwei.service.NewsService;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    
	private EditText titleText;
    private EditText lengthText;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        titleText = (EditText) this.findViewById(R.id.title);
        lengthText = (EditText) this.findViewById(R.id.timelength);
    }
    
    public void save(View v){
    	String title = titleText.getText().toString();
    	String length = lengthText.getText().toString();
    	boolean result = NewsService.save(title, length);
    	if(result){
    		Toast.makeText(getApplicationContext(), R.string.success, 1).show();
    	}else{
    		Toast.makeText(getApplicationContext(), R.string.error, 1).show();
    	}
    	
    	}
    
}