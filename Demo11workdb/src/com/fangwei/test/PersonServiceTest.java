package com.fangwei.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.domain.Person;
import com.fangwei.otherdb.DBOpenHelper;
import com.fangwei.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTes";
	public void testDBCreate() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this.getContext());
//		dbOpenHelper.getReadableDatabase();
		dbOpenHelper.getWritableDatabase();
		//<��>/databases/itcast.db
		
	}
	
	/**
	 * �����Է�ҳ�ṩ����
	 * @throws Exception
	 */
	public void testSave01() throws Exception {
		PersonService service  = new PersonService(getContext());
		for(int i =1 ; i < 20 ; i++){
			Person person = new Person(1,"fangwei","15827241251");
			service.save(person);
			//���ļ����¿� .db��SQLlite��� �� �� �Ƿ����
		}
	
	}
	
	/**
	 * ��ȡ��¼
	 * @param id
	 * @throws Exception
	 */
	public void testfind() throws Exception {
		PersonService service  = new PersonService(getContext());
		Person person = service.find(1);
		//��д person��tostring����
		Log.i(TAG,person.toString());
		
	}

	


	/**
	 * 
	 */
	public void testupdateAmount() throws Exception {
		PersonService service = new PersonService(getContext());
		Person person = service.find(1);
		person.setAmount(100);
		service.update(person);
	
		person = service.find(2);
		person.setAmount(50);
		service.update(person);
		//���Է��� :ִ�и÷��� Ȼ�� ִ�� ��������  �Ƿ���19��
	}
	
	public void testPayment() {
		PersonService service = new PersonService(getContext());
		service.payment();
	}
	
	
}
