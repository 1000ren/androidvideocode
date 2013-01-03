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
	        textView.setBackgroundResource(R.drawable.frame);//����Ҳ��һ��ͼ����Դ
	        drawable = (AnimationDrawable) textView.getBackground();
	    }

	    /**
	     * �����û�������ʱ�� �����䲥��
	     */
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				drawable.start();//���Ŷ���
				return true;
			}
			return super.onTouchEvent(event);
		}
	    
	    
	}