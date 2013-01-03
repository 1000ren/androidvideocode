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
    
       //��λListView
       //������ ���ݰ󶨵�ʱʹ��
       
       ListView listView =(ListView) this.findViewById(R.id.listView);
        
        
       personService = new PersonService(this);
       
       //-------�����ǹ�����---------------------------------------
       
       
       
       
   //��һ��cursor�ķ�ʽ
    /*   
    
       List<Person> persons = personService.getScrollData(0, 20);
    
       //��һ��map���ϸ�������������
       List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
       
       for(Person person : persons){
    	   HashMap<String , Object> item = new HashMap<String,Object>();
    	   item.put("id", person.getId());
    	   item.put("name", person.getName());
    	   item.put("phone", person.getPhone());
    	   item.put("amount", person.getAmount());
    	   //������ ����  ������� һ�ж��׷���
    	   data.add(item);
       }
       
       
         	//����������  id ָ���󶨵��Ǹ���Ŀ��
        
       	SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item,
    		   new String[]{"name","phone","amount"},new int[]{R.id.name,R.id.phone,R.id.amount});
       	listView.setAdapter(adapter);		
    
      */ 	
       
       
       
       	// ������һ�� Cursor
       	
       Cursor cursor = personService.getCursorScrollData(0, 20);
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.item,cursor,
    		   new String[]{"name","phone","amount"},new int[]{R.id.name,R.id.phone,R.id.amount});
       listView.setAdapter(adapter);
       	
       	
       
       
       //����һ��������¼�
       	listView.setOnItemClickListener(new ItemClickListener());
       	
 
//������ʾ�ҵ�ԭ��
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
//��һ�� cursor ��Ӧ�ķ���
//    		HashMap<String,Object> data = (HashMap<String,Object>) listView.getItemAtPosition(position);
//    		String personid = data.get("id").toString();
//    		Toast.makeText(getApplicationContext(), personid, 1).show();
    	
    	
//�ڶ��� SimpleCursorAdapter
    	Cursor c  =  (Cursor) listView.getItemAtPosition(position);	
    	String name = c.getString(c.getColumnIndex("name"));
    	Toast.makeText(getApplicationContext(), name, 1).show();
    	
    	}
		
    	
    }
    
    
}