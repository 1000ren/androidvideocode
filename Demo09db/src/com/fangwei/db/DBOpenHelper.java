package com.fangwei.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context) {
		//version 版本号  绝对不能为0
//		super(context, "fangwei.db", null, 1);
		//将上面的版本号 由 1  ----> 2 那么底下的方法   将被调用
		super(context, "fangwei.db", null, 4);
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {//特别象DBUTIL
		//创建表的SQL语句
		db.execSQL("CREATE TABLE person (id integer primary key autoincrement, name varchar(20))");

	}

	@Override
	//数据库的版本号 发生改变的时候 此方法 才会被调用
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//在已存在 SQ表中 添加一个
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
		//测试是否会自动创建数据库
	}

}
