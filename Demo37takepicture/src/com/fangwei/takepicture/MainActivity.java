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
	//加载指南针
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
	        
	 
	        //加载指南针
	        imageView = (ImageView) findViewById(R.id.imageview);

			SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
			sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
					SensorManager.SENSOR_DELAY_FASTEST);
	        
	        
	        
	        //========================透明吧
//	        Window window=getWindow();
//	        WindowManager.LayoutParams wl = window.getAttributes();
//	        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
//	        wl.alpha=0.3f;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
//	        window.setAttributes(wl);

	        
	        
	        //======================================================
	        
	        /*
			 * 初始化地图Activity/使用key
			 * 
			 */
//			//增加以下代码
//			mBMapMan = new BMapManager(getApplication());
//			//keywe为到百度申请的
//			mBMapMan.init("69815DB52E64578CC39354AE55971647EB091BFB", null);
//			super.initMapActivity(mBMapMan);
//		    
//			mMapView = (MapView) findViewById(R.id.bmapsView);
//			mMapView.setBuiltInZoomControls(true);  //设置启用内置的缩放控件
//			mMapView.setBackgroundColor(60000000); 
//			MapController mMapController = mMapView.getController();  // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
//			GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
//				(int) (116.404 * 1E6));  //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
//			mMapController.setCenter(point);  //设置地图中心点
//			mMapController.setZoom(18);    //设置地图zoom级别
//			
//
//
//			// 初始化Location模块
//			mLocationManager = mBMapMan.getLocationManager();
//			// 通过enableProvider和disableProvider方法，选择定位的Provider
//			// mLocationManager.enableProvider(MKLocationManager.MK_NETWORK_PROVIDER);
//			// mLocationManager.disableProvider(MKLocationManager.MK_GPS_PROVIDER);
//			// 添加定位图层
//			MyLocationOverlay mylocTest = new MyLocationOverlay(this, mMapView);
//			mylocTest.enableMyLocation(); // 启用定位
//			mylocTest.enableCompass();    // 启用指南针
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
				 	camera = Camera.open();//打开摄像头
					Camera.Parameters parameters = camera.getParameters();
					//Log.i("MainActivity", parameters.flatten());
					parameters.setPreviewSize(800, 480);
					parameters.setPreviewFrameRate(5);
					parameters.setPictureSize(1024,768);
					parameters.setJpegQuality(80);
					camera.setParameters(parameters);
					camera.setPreviewDisplay(holder);
					
					camera.startPreview();//开始预览
					
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
	     * 触摸事件  告诉屏幕是否显示
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
		 * Override以下方法,管理API:
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