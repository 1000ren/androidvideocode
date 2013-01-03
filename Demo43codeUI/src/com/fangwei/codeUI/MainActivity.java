package com.fangwei.codeUI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        
        TextView textView = new TextView(this);
        textView.setText(R.string.hello);
        textView.setId(100);
        
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,
        		ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(textView, layoutParams);
        	
        //添加固定的相对布局
        View partView = getPartLayout();
        linearLayout.addView(partView);
        
        layoutParams = new ViewGroup.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT, 
        		ViewGroup.LayoutParams.MATCH_PARENT);
        
        setContentView(linearLayout, layoutParams);
    }
    
    
    /**
     * 获取相对固定的界面部分
     * @return
     */
    private View getPartLayout(){
    	LayoutInflater layoutInflater =
    			(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	return layoutInflater.inflate(R.layout.part, null);
    }
}