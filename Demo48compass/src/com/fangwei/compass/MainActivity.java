package com.fangwei.compass;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageView ;
    private SensorManager manager ;
    private SensorListener listener = new SensorListener(); 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imageView = (ImageView) this.findViewById(R.id.imageview);
        imageView.setKeepScreenOn(true);
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }
    
    
    @Override
    protected void onResume() {
    	Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    	manager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_GAME);
    	super.onResume();
    }
    
    
    @Override
    protected void onPause() {
    	manager.unregisterListener(listener);
    	super.onPause();
    }
    

	
		
	/**
	 * 传感器类
	 * @author hp
	 *
	 */
	private final class SensorListener implements SensorEventListener{
		private float predegree = 0 ;
		public void onSensorChanged(SensorEvent event) {
			//存方方向值90
			float degree = event.values[0];
			RotateAnimation animation = new RotateAnimation(predegree, -degree,
					Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
			animation.setDuration(200);
			imageView.startAnimation(animation);
			predegree = -degree ;
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	
	
	
}