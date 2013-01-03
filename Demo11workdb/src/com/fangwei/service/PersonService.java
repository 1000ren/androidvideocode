package com.fangwei.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangwei.domain.Person;
import com.fangwei.otherdb.DBOpenHelper;

public class PersonService {
	
//	private Context context;
	DBOpenHelper dbOpenHelper;
	public PersonService(Context context) {
//		this.context=context;
		dbOpenHelper = new DBOpenHelper(context);
	}
	
	/**
	 * 银行转账
	 * @throws Exception
	 */
	public void payment() {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		try{
		db.beginTransaction();//开启事务
		db.execSQL("update person set amount = amount - 10 where id = 1");
		db.execSQL("update person set amount = amount + 10 where id = 2");
		db.setTransactionSuccessful();//把事务设为True
		}finally{
		db.endTransaction();//结束事务  是提交事务 还是回滚事务 是由事务的标志 决定
		//如果事务的标志为True（成功），就会提交事务，否则回滚事务，默认情况下事务的标志位Flase
	}
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
		db.execSQL("update person set name=?,phone=?,amount=? where id=?", 
				new Object[]{person.getName(), person.getPhone(), person.getAmount(), 
				person.getId()});
	}
	
	/**
	 * 获取记录
	 * @param id 记录id
	 * @return
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where id =?", 
				new String[]{id.toString()});
		//判断 移到第一条记录 是否有记录
		if(cursor.moveToFirst()){
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			cursor.close();
			return new Person(id, name, phone, amount);
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
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			persons.add(new Person(id, name, phone, amount));
		}
		//关闭cursor对象
		cursor.close();
		return persons;
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

