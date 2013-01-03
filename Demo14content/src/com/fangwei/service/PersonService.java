package com.fangwei.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangwei.content.DBOpenHelper;
import com.fangwei.domain.Person;


public class PersonService {
	private DBOpenHelper dbOpenHelper;
	
	public PersonService(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}
		
	/**
	 * 保存数据
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into person(name,phone,amount) values(?,?,?)",
				new Object[]{person.getName(), person.getPhone(), person.getAmount()});
		//db.close();
	}
	
	/**
	 * 分页获取记录
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取的记录数
	 * @return
	 */
	//分页
	public List<Person> getScrollData(int offset, int maxResult)
	{
		
		
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person order by id asc limit ?,?", 
				new String[]{String.valueOf(offset),String.valueOf(maxResult)} );
		
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			persons.add(new Person(id, name, phone, amount));
		}
		//关闭cursor对象
		cursor.close();
		return persons;
	}
	
	
	/**
	 * 分页获取记录  配合新 的SimpleCursorAdapter对象是使用
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取的记录数
	 * @return
	 */
	//分页
	public Cursor getCursorScrollData(int offset, int maxResult)
	{
		
		
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		
		return db.rawQuery("select id as _id,phone,name,amount from person order by id asc limit ?,? ", 
				new String[] {String.valueOf(offset),String.valueOf(maxResult)});
	}
	

}
