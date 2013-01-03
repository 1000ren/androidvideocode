package com.fangwei.service.client;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangwei.adil.QueryService;

public class MainActivity extends Activity {
	private EditText numberText;
	 private TextView resultView;
	private StudentConnection conn = new StudentConnection();
	private QueryService queryService;
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       
       numberText = (EditText) this.findViewById(R.id.number);
       resultView = (TextView) this.findViewById(R.id.resultView);
       Intent service = new Intent("cn.itcast.student.query");
       bindService(service, conn, BIND_AUTO_CREATE);
   }
   
   private final class StudentConnection implements ServiceConnection{
		public void onServiceConnected(ComponentName name, IBinder service) {
			queryService = QueryService.Stub.asInterface(service);
		}
		public void onServiceDisconnected(ComponentName name) {
			queryService = null;
		}
   	
   }
   
   public void query(View v){
   	String number = numberText.getText().toString();
		try {
			String name = queryService.queryStudent(Integer.valueOf(number));
			resultView.setText(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	
   }
}