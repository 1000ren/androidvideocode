package com.fangwei.listview;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.fangwei.service.PersonService;

public class MainActivity extends Activity {
	private PersonService personService;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
       //定位ListView
       //适配器 数据绑定的时使用
       
       ListView listView =(ListView) this.findViewById(R.id.listView);
        
        
       personService = new PersonService(this);
       
       //-------上面是公共段---------------------------------------
       
       
       
       
   //第一种cursor的方式
    /*   
    
       List<Person> persons = personService.getScrollData(0, 20);
    
       //建一个map集合给适配器传参数
       List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
       
       for(Person person : persons){
    	   HashMap<String , Object> item = new HashMap<String,Object>();
    	   item.put("id", person.getId());
    	   item.put("name", person.getName());
    	   item.put("phone", person.getPhone());
    	   item.put("amount", person.getAmount());
    	   //别忘了 这条  如果忘了 一切都白费了
    	   data.add(item);
       }
       
       
         	//第三个参数  id 指定绑定到那个条目中
        
       	SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item,
    		   new String[]{"name","phone","amount"},new int[]{R.id.name,R.id.phone,R.id.amount});
       	listView.setAdapter(adapter);		
    
      */ 	
       
       
       
       	// 介绍另一种 Cursor
       	
       Cursor cursor = personService.getCursorScrollData(0, 20);
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.item,cursor,
    		   new String[]{"name","phone","amount"},new int[]{R.id.name,R.id.phone,R.id.amount});
       listView.setAdapter(adapter);
       	
       	
       
       
       //设置一个点击的事件
       	listView.setOnItemClickListener(new ItemClickListener());
       	
 
//底下演示我的原理
//       	for(int i=0;i<data.size();i++){
//       		View itemView = adapter.getView(listview, convertView, parent)
//       	}
    }
    
    //
    private final class ItemClickListener implements OnItemClickListener
    {
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id) {
    		ListView listView  =(ListView) parent;
//第一种 cursor 对应的方法
//    		HashMap<String,Object> data = (HashMap<String,Object>) listView.getItemAtPosition(position);
//    		String personid = data.get("id").toString();
//    		Toast.makeText(getApplicationContext(), personid, 1).show();
    	
    	
//第二种 SimpleCursorAdapter
    	Cursor c  =  (Cursor) listView.getItemAtPosition(position);	
    	String name = c.getString(c.getColumnIndex("name"));
    	Toast.makeText(getApplicationContext(), name, 1).show();
    	
    	}
		
    	
    }
    
    
}