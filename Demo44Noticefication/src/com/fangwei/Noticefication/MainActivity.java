package com.fangwei.Noticefication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	 private EditText shorttitleText;
	    private EditText titleText;
	    private EditText contentText;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        shorttitleText = (EditText) this.findViewById(R.id.shorttitle);
	        titleText = (EditText) this.findViewById(R.id.title);
	        contentText = (EditText) this.findViewById(R.id.content);
	    }
	    
	    public void send(View v){
	    	String tickerText = shorttitleText.getText().toString();
	    	String title = titleText.getText().toString();
	    	String content = contentText.getText().toString();
	    	int icon = android.R.drawable.stat_notify_chat;
	    	Notification notification = new Notification(icon, tickerText, System.currentTimeMillis());
	    	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:194949494"));
	    	
	    	PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, 0);
	    	
	    	notification.setLatestEventInfo(this, title, content, pendingIntent);//设置通知的参数
	    	notification.defaults = Notification.DEFAULT_SOUND;//设定通知的默认图标
	    	//通知被点击后，取消通知的图标
	    	notification.flags = Notification.FLAG_AUTO_CANCEL;
	    	//通知管理器
	    	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    	manager.notify(100, notification);
	    }
	}