package com.fangwei.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
	private DBOpenHelper dbOpenHelper;
	//不匹配的返回码
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	//对于insert方法
	private static final int PERSONS = 1 ;
	//对应delete方法
	private static final int PERSON = 2 ;
	static{
		matcher.addURI("com.fangwei.content.personprovider", "person", PERSONS);
		matcher.addURI("com.fangwei.content.personprovider", "person/#",PERSON );
	}

	
	
	
	/**
	 * 由系统调用的 在内容调用的生命周期内只会被调用一次
	 */
	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(getContext());
		return false;
	}
	
	
	/**
	 * Uri代表要操作的数据，Uri主要包括了两个部分的信息：
	 * 1> 需要操作的ContentProvider
	 * 2> 对ContentProvider中的什么数据进行操作，一个Uri由以下就几个部分组成
	 * content:/c
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int num = 0;
		switch (matcher.match(uri)) {
		case PERSONS:
			num = db.delete("person", selection, selectionArgs);
			break;
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "id =" +id;
			if(selection!=null && !"".equals(selection.trim())){
				where += " and "+selection;
			}
			num = db.delete("person", where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
		return num;
	}


	/**
	 * 下面的应用代表允许外部的应用对该该应用的数据进行：增删改查
	 * 
	 * 不一定要去全部去实现
	 */
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case PERSONS://com.fangwei.providers.personprovider/person
			//返回是新增记录的id
			long rowid = db.insert("person", "id", values);
			//格式： content://com.fangwei.providers.persnprovider/person/10
			/*
			 * 内容提供者发送通知
			 */
			getContext().getContentResolver().notifyChange(uri, null);
			return ContentUris.withAppendedId(uri, rowid);
			
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
			
		}
	}


	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		//
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int num = 0;
		switch (matcher.match(uri)) {
		case PERSONS:
			num = db.update("person", values, selection, selectionArgs);
			break;
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "id=" + id ;
			if(selection!=null&&!"".equals(selection.trim())){
				where += " and "+selection;
			}
			num = db.update("person", values, where, selectionArgs);
			break;
			
		default:
			throw new IllegalAccessError("Unknown Uri:"+uri);
		}
		return num;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case PERSONS:
			return db.query("person", projection,selection, selectionArgs, null,null ,sortOrder);
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "id=" + id;
			if(selection!=null && !"".equals(selection.trim())){
				where +=" and " + selection; 
			}
			return db.query("person", projection, where, selectionArgs, null, null, sortOrder);
		
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}


	
	/**
	 * 为我们要共享的数据定义一个数据类型
	 */
	@Override
	public String getType(Uri uri) {
		
		switch (matcher.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/person";
		case PERSON:
			return "vnd.android.cursor.item/person";
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}

}
