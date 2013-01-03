package com.fangwei.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context) {
		//version �汾��  ���Բ���Ϊ0
//		super(context, "fangwei.db", null, 1);
		//������İ汾�� �� 1  ----> 2 ��ô���µķ���   ��������
		super(context, "fangwei.db", null, 4);
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {//�ر���DBUTIL
		//�������SQL���
		db.execSQL("CREATE TABLE person (id integer primary key autoincrement, name varchar(20))");

	}

	@Override
	//���ݿ�İ汾�� �����ı��ʱ�� �˷��� �Żᱻ����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//���Ѵ��� SQ���� ���һ��
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
		//�����Ƿ���Զ��������ݿ�
	}

}
