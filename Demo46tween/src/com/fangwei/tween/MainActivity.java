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
       //͸��Ч��           ʹ��alpha.xml���ɶ���Ч������
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
       //ƽ��Ч��
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
       //����Ч��
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
       //��תЧ��
        // Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        
        //����ʵ����ת��Ч��
       /* Animation animation = new RotateAnimation(0, 360, 
        		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(5000);*/
        
        //���Ч��
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fangwei);
        //��ͼƬͣ���ڽ�����״̬
        animation.setFillAfter(true);//
        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.startAnimation(animation);
    }
}