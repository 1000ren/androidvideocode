package com.fangwei.remoteService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class StudentService extends Service {
	private String[] names = {"����", "��С��", "����"};
	private IBinder binder = new StudentBinder();
	
	
	private String query(int no){
		if(no > 0 && no <4){
			return names[no - 1];
		}
		return null;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	private final class StudentBinder extends QueryService.Stub{
		public String queryStudent(int no) throws RemoteException {
			return query(no);
		}
	}
	
}