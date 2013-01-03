package com.fangwei.studentQuery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class StudentService extends Service {
	private IBinder binder = new StudentBinder();
	private String [] names = {"张明","李小龙","赵云"};
	
	private String query(int no){
		if(no > 0 && no <4){
			return names[no-1];
		}
		return null;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	
	
	
	private final class StudentBinder extends  Binder implements QueryService{

		public String queryStudent(int no) {
			return query(no);
		}
		
	}
}
