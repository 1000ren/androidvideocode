package com.fangwei.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fangwei.domain.News.News;
import com.fangwei.service.videoNewService;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView listview = (ListView) this.findViewById(R.id.listview);
        try{
        	List<News> videos = videoNewService.getLastNews();
        	List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        	for(News news : videos){
        		HashMap<String,Object> item = new HashMap<String,Object>();
        		item.put("id", news.getId());
        		item.put("title", news.getTitle());
        		item.put("timelength", getResources().getString(R.string.timelength)
        				+news.getTimelength()+getResources().getString(R.string.min));
        		data.add(item);
        	}
        	SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item
        			,new String[]{"title","timelength"},new int[]{R.id.title,R.id.timelength});
        	listview.setAdapter(adapter);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
    }
}