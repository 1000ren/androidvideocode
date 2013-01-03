package com.fangwei.takepicture;

import java.util.ArrayList;
import java.util.List;
 
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
 
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MKPlanNode;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.PoiOverlay;
import com.baidu.mapapi.RouteOverlay;
import com.baidu.mapapi.TransitOverlay;

import java.io.File;
import java.io.FileOutputStream;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

public class MainActivity extends MapActivity implements SensorEventListener{
	//����ָ����
	private ImageView imageView;
	private float currentDegree = 0f;
 
	
	
	BMapManager mBMapMan = null;
	MapView mMapView;
	MKLocationManager mLocationManager;
	private View layout;
	    private Camera camera;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.main);
	        
	        layout = this.findViewById(R.id.buttonlayout);
	        
	        
	        SurfaceView surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
	        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        surfaceView.getHolder().setFixedSize(176, 144);
	        surfaceView.getHolder().setKeepScreenOn(true);
	        surfaceView.getHolder().addCallback(new SurfaceCallback());
	        
	 
	        //����ָ����
	        imageView = (ImageView) findViewById(R.id.imageview);

			SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
			sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
					SensorManager.SENSOR_DELAY_FASTEST);
	        
	        
	        
	        //========================͸����
//	        Window window=getWindow();
//	        WindowManager.LayoutParams wl = window.getAttributes();
//	        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
//	        wl.alpha=0.3f;//���������ô�����ؼ���͸���ȵģ���.��ȫ͸������.����͸����
//	        window.setAttributes(wl);

	        
	        
	        //======================================================
	        
	        /*
			 * ��ʼ����ͼActivity/ʹ��key
			 * 
			 */
//			//�������´���
//			mBMapMan = new BMapManager(getApplication());
//			//keyweΪ���ٶ������
//			mBMapMan.init("69815DB52E64578CC39354AE55971647EB091BFB", null);
//			super.initMapActivity(mBMapMan);
//		    
//			mMapView = (MapView) findViewById(R.id.bmapsView);
//			mMapView.setBuiltInZoomControls(true);  //�����������õ����ſؼ�
//			mMapView.setBackgroundColor(60000000); 
//			MapController mMapController = mMapView.getController();  // �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
//			GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
//				(int) (116.404 * 1E6));  //�ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
//			mMapController.setCenter(point);  //���õ�ͼ���ĵ�
//			mMapController.setZoom(18);    //���õ�ͼzoom����
//			
//
//
//			// ��ʼ��Locationģ��
//			mLocationManager = mBMapMan.getLocationManager();
//			// ͨ��enableProvider��disableProvider������ѡ��λ��Provider
//			// mLocationManager.enableProvider(MKLocationManager.MK_NETWORK_PROVIDER);
//			// mLocationManager.disableProvider(MKLocationManager.MK_GPS_PROVIDER);
//			// ��Ӷ�λͼ��
//			MyLocationOverlay mylocTest = new MyLocationOverlay(this, mMapView);
//			mylocTest.enableMyLocation(); // ���ö�λ
//			mylocTest.enableCompass();    // ����ָ����
//			mMapView.getOverlays().add(mylocTest);
			
			
		//========================================================================================
			
			
			
	    
	    }
	    
	    
	    
	    
	    
	    
	    
	    public void takepicture(View v){
	    	if(camera!=null){
	    		switch (v.getId()) {
	    		case R.id.takepicture:
	    			camera.takePicture(null, null, new MyPictureCallback());
	    			
	    			break;

	    		case R.id.autofocus:
	    			
	    			camera.autoFocus(null);
	    			
	    			break;
	    		}
	    	}
	    }
	    
	    private final class MyPictureCallback implements PictureCallback{
			public void onPictureTaken(byte[] data, Camera camera) {
				try {
					File jpgFile = new File(Environment.getExternalStorageDirectory(), 
							System.currentTimeMillis()+".jpg");
					FileOutputStream outStream = new FileOutputStream(jpgFile);
					outStream.write(data);
					outStream.close();
					camera.startPreview();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    	
	    }
	    
	    private final class SurfaceCallback implements Callback{
			public void surfaceCreated(SurfaceHolder holder) {
				try{
				 	camera = Camera.open();//������ͷ
					Camera.Parameters parameters = camera.getParameters();
					//Log.i("MainActivity", parameters.flatten());
					parameters.setPreviewSize(800, 480);
					parameters.setPreviewFrameRate(5);
					parameters.setPictureSize(1024,768);
					parameters.setJpegQuality(80);
					camera.setParameters(parameters);
					camera.setPreviewDisplay(holder);
					
					camera.startPreview();//��ʼԤ��
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void surfaceChanged(SurfaceHolder holder, 
					int format, int width,int height) {
			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				if(camera!=null){
					camera.release();
					camera = null;
				}
			}
	    	
	    }

	    
	    /**
	     * �����¼�  ������Ļ�Ƿ���ʾ
	     */
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				layout.setVisibility(ViewGroup.VISIBLE);
				
				return true;
			}
			return super.onTouchEvent(event);
		}
	    
		
	
		
		/**
		 * Override���·���,����API:
		 */
		@Override
		protected boolean isRouteDisplayed() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		protected void onDestroy() {
		    if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		    }
		    super.onDestroy();
		}
		@Override
		protected void onPause() {
		    if (mBMapMan != null) {
			mBMapMan.stop();
		    }
		    super.onPause();
		}
		@Override
		protected void onResume() {
		    if (mBMapMan != null) {
			mBMapMan.start();
		    }
		    super.onResume();
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ORIENTATION)
			{  
				
				float degree = event.values[0];	
				RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				ra.setDuration(200);
				
				imageView.startAnimation(ra); 
				currentDegree = -degree;

			}
			
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	}