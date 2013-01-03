package com.fangwei.service;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangwei.domain.Person;
import com.fangwei.otherdb.DBOpenHelper;

public class OtherPersonService {
	private DBOpenHelper dbOpenHelper;
	public OtherPersonService(Context context){
		dbOpenHelper = new DBOpenHelper(context);
	}
	
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
 		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		
		//第二参数 构造 insert语句的添加  空值字段名称
//		db.insert("person", "name", values);
		//insert into person（...）vlaues()
		//insert into person(id) values(null) -->
		//在SQLliter实际在这里还是自增长的值
		
		//所有第二字段是没有必要指定的
		db.insert("person", null, values);	
	}
	
	/**
	 * 删除记录
	 * @param id 记录ID
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.delete("person", "id=?", new String[]{id.toString()});
	}
	
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		db.update("person",values ,"id=?", new String[]{person.getId().toString()});
	}
	
	/**
	 * 获取记录
	 * @param id 记录ID
	 */
	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query("person",null, "id=?",new String[]{id.toString()}, null, null, null);
		
		if(cursor.moveToFirst()){
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String name =cursor.getString(cursor.getColumnIndex("name"));
			cursor.close();
			return new Person(id,name,phone);
		}
		return null;
	
	}
	
	/**
	 * 分页获取记录
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取的记录数
	 * @return
	 */
	public List<Person>  getScrollData(int offset,int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query("person", null, null, null, null, null, "id asc",offset+","+maxResult);
		
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			persons.add(new Person(id,name,phone));
		}
		cursor.close();
		return persons;
	}
	
	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query("person", new String[]{"count(*)"}, null, null, null, null, null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		return count;
	}
	
}











