package com.fangwei.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";   
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate()");
    }
    
    public void openPauseActivity(View v){
    	Intent intent = new Intent(this, PauseActivity.class);
    	startActivity(intent);
    }

    public void openStopActivity(View v){
    	Intent intent = new Intent(this, StopActivity.class);
    	startActivity(intent);
    }
    
	@Override
	protected void onStart() {
		Log.i(TAG, "onStart()");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy()");
		super.onDestroy();
	}
}