package com.fangwei.FrameAnimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity {
	 private AnimationDrawable drawable;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        TextView textView = (TextView) this.findViewById(R.id.textView);
	        textView.setBackgroundResource(R.drawable.frame);//动画也是一种图形资源
	        drawable = (AnimationDrawable) textView.getBackground();
	    }

	    /**
	     * 设置用户触摸的时候 ，让其播放
	     */
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				drawable.start();//播放动画
				return true;
			}
			return super.onTouchEvent(event);
		}
	    
	    
	}