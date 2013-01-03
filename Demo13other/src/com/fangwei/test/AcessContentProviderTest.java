package com.fangwei.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class AcessContentProviderTest extends AndroidTestCase {
	//给日志增加一个常量
	private static final String TAG = "AcessContentProviderTest";
	
	
	public void testInsert() throws Exception {
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.fangwei.providers.personprovider/person/6");
//		                     content://com.fangwei.providers.personprovider/person/2
		ContentValues values = new ContentValues();
		values.put("id", "3");
		values.put("name", "contentprovider2ee2e2");
		values.put("phone", "5554");
		values.put("amount", "1");
		resolver.insert(uri, values);
	}
	
	/**
	 *  测试删除delete（）方法 
	 * @throws Exception
	 */
	public void testDelete() throws Exception {
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.fangwei.providers.personprovider/person/1");
		resolver.delete(uri, null, null);
	}
	
	/**
	 * 测试 更新update（）方法
	 * 
	 */
	public void testUpdate() throws Exception {
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.fangwei.providers.personprovider/person/9");
		ContentValues values = new ContentValues();
		values.put("name", "1000ren");
		resolver.update(uri,values,null,null);
	}
	
	public void testQuery() throws Exception {
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.fangwei.providers.personprovider/person");
		Cursor cursor = resolver.query(uri, null, null, null, "id asc");
		if(cursor.moveToNext()){
			Log.i(TAG, cursor.getString(cursor.getColumnIndex("name")));
		}
			System.err.println("-----你要的类型-----"+resolver.getType(uri));
		cursor.close();
	}
	
}
