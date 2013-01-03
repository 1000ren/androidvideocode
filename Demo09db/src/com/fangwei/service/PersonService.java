package com.fangwei.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangwei.db.DBOpenHelper;
import com.fangwei.domain.Person;

public class PersonService {
	
//	private Context context;
	DBOpenHelper dbOpenHelper;
	public PersonService(Context context) {
//		this.context=context;
		dbOpenHelper = new DBOpenHelper(context);
	}
	
	/**
	 * 保存数据
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
		//SQLiteDatabase db2 = dbOpenHelper.getWritableDatabase();
		//注意两次调用 db 和 db2 都是同一个对象
		//		db.execSQL("insert into person(name,phone) value('"+person.getName()+"','"+person.getPhone()+"')");
		//注意上面的语句是会 隐患的  如果用户在输入单引号的符号 就会 出错
		db.execSQL("insert into person(name,phone) values(?,?)",
				new Object[]{person.getName(),person.getPhone()});
//	关闭数据库 ---但是此处是不必要  不关 会 一定提高性能
//		db.close();
	}
	
	
	/**
	 * 删除记录
	 * @param id 记录ID
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where id=?",new Object[]{id});
	}
	
	
	/**
	 * 更新记录
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update person set name=? phone=?",
				new Object[]{person.getName(),person.getId()});
	}
	
	/**
	 * 获取记录
	 * @param id 记录id
	 * @return
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where id =?", new String[]{id.toString()});
		//判断 移到第一条记录 是否有记录
		if(cursor.moveToFirst()){
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			//关掉cursor
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
			String name = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(id,name,phone));
		}
		//关闭cursor对象
		cursor.close();
		return null;
	}
	
	/**
	 * 获取记录总数
	 * @return
	 */
	//用于取得记录的总数
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		return count;
	}
}

/*
 * 看下 getWritableDatabase() 与 getReadableDatabase()的区别
 * 
 * getReadableDatabase()
 * 最大容量 达到的时候 ---- 只读方式 打开数据库
 * 
 * 没满 时候  就 用  getWritableDatabase()读写
 * 
 * 两个都可以完成读和写
 */

