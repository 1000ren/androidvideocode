package com.fangwei.videorecoder;

import java.io.File;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	 private SurfaceView surfaceView;
	    private MediaRecorder mediaRecorder;
	    private Button recordbutton;
	    private Button stopbutton;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.main);
	        
	        recordbutton = (Button) this.findViewById(R.id.recordbutton);
	        stopbutton = (Button) this.findViewById(R.id.stopbutton);
	        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
	        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        surfaceView.getHolder().setFixedSize(176, 144);
	        surfaceView.getHolder().setKeepScreenOn(true);
	    }
	    
	    public void recordvideo(View v){
	    	if(v.getId() == R.id.recordbutton){
	    		try{
		    		File videoFile = new File(Environment.getExternalStorageDirectory(), 
		    				System.currentTimeMillis()+".3gp");
		    		mediaRecorder = new MediaRecorder();
		    		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		    		mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		    		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		    		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		    		mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		    		mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		    		mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
		    		mediaRecorder.prepare();
		    		mediaRecorder.start();//¿ªÊ¼Â¼ÖÆ
		    		
		    		recordbutton.setEnabled(false);
		    		stopbutton.setEnabled(true);
	    		}catch (Exception e) {
					e.printStackTrace();
				}
	    	}else{
	    		if(mediaRecorder!=null){
	    			mediaRecorder.release();
	    			mediaRecorder = null;
	    			recordbutton.setEnabled(true);
		    		stopbutton.setEnabled(false);
	    		}
	    	}
	    }
	}