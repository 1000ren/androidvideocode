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
	//��ƥ��ķ�����
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	//����insert����
	private static final int PERSONS = 1 ;
	//��Ӧdelete����
	private static final int PERSON = 2 ;
	static{
		matcher.addURI("com.fangwei.content.personprovider", "person", PERSONS);
		matcher.addURI("com.fangwei.content.personprovider", "person/#",PERSON );
	}

	
	
	
	/**
	 * ��ϵͳ���õ� �����ݵ��õ�����������ֻ�ᱻ����һ��
	 */
	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(getContext());
		return false;
	}
	
	
	/**
	 * Uri����Ҫ���������ݣ�Uri��Ҫ�������������ֵ���Ϣ��
	 * 1> ��Ҫ������ContentProvider
	 * 2> ��ContentProvider�е�ʲô���ݽ��в�����һ��Uri�����¾ͼ����������
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
	 * �����Ӧ�ô��������ⲿ��Ӧ�öԸø�Ӧ�õ����ݽ��У���ɾ�Ĳ�
	 * 
	 * ��һ��Ҫȥȫ��ȥʵ��
	 */
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case PERSONS://com.fangwei.providers.personprovider/person
			//������������¼��id
			long rowid = db.insert("person", "id", values);
			//��ʽ�� content://com.fangwei.providers.persnprovider/person/10
			/*
			 * �����ṩ�߷���֪ͨ
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
	 * Ϊ����Ҫ��������ݶ���һ����������
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
