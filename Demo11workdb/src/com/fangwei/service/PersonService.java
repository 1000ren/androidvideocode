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
	 * ����ת��
	 * @throws Exception
	 */
	public void payment() {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		try{
		db.beginTransaction();//��������
		db.execSQL("update person set amount = amount - 10 where id = 1");
		db.execSQL("update person set amount = amount + 10 where id = 2");
		db.setTransactionSuccessful();//��������ΪTrue
		}finally{
		db.endTransaction();//��������  ���ύ���� ���ǻع����� ��������ı�־ ����
		//�������ı�־ΪTrue���ɹ������ͻ��ύ���񣬷���ع�����Ĭ�����������ı�־λFlase
	}
		}
	
	
	/**
	 * ��������
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into person(name,phone,amount) values(?,?,?)",
				new Object[]{person.getName(), person.getPhone(), person.getAmount()});
		//db.close();
	}
	

	/**
	 * ɾ����¼
	 * @param id ��¼ID
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where id=?",new Object[]{id});
	}
	
	
	/**
	 * ���¼�¼
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update person set name=?,phone=?,amount=? where id=?", 
				new Object[]{person.getName(), person.getPhone(), person.getAmount(), 
				person.getId()});
	}
	
	/**
	 * ��ȡ��¼
	 * @param id ��¼id
	 * @return
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where id =?", 
				new String[]{id.toString()});
		//�ж� �Ƶ���һ����¼ �Ƿ��м�¼
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
	 * ��ҳ��ȡ��¼
	 * @param offset ����ǰ���������¼
	 * @param maxResult ÿҳ��ȡ�ļ�¼��
	 * @return
	 */
	//��ҳ
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
		//�ر�cursor����
		cursor.close();
		return persons;
	}
	
	/**
	 * ��ȡ��¼����
	 * @return
	 */
	//����ȡ�ü�¼������
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		return count;
	}
}

/*
 * ���� getWritableDatabase() �� getReadableDatabase()������
 * 
 * getReadableDatabase()
 * ������� �ﵽ��ʱ�� ---- ֻ����ʽ �����ݿ�
 * 
 * û�� ʱ��  �� ��  getWritableDatabase()��д
 * 
 * ������������ɶ���д
 */

