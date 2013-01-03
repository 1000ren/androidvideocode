package com.fangwei.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       //透明效果           使用alpha.xml生成动画效果对象
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
       //平移效果
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
       //缩放效果
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
       //旋转效果
        // Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        
        //编码实现旋转的效果
       /* Animation animation = new RotateAnimation(0, 360, 
        		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(5000);*/
        
        //混合效果
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fangwei);
        //让图片停留在结束的状态
        animation.setFillAfter(true);//
        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.startAnimation(animation);
    }
}