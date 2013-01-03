package com.fangwei.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.db.DBOpenHelper;
import com.fangwei.domain.Person;
import com.fangwei.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTes";
	public void testDBCreate() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this.getContext());
//		dbOpenHelper.getReadableDatabase();
		dbOpenHelper.getWritableDatabase();
		//<��>/databases/itcast.db
		
	}
	
	public void testSave() throws Exception {
		PersonService service  = new PersonService(getContext());
		Person person = new Person("fangwei","15827241251");
		service.save(person);
		//���ļ����¿� .db��SQLlite��� �� �� �Ƿ����
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
	 * ���¼�¼
	 * @throws Exception
	 */
	public void testUpdate() throws Exception {
		PersonService service = new PersonService(getContext());
		Person person = service.find(1);
		person.setName("fangwei110");
		service.update(person);
		//���Է�ʽ ��ִ�и÷���  ----�ٴ� ִ�� �����testfind��������
	}
	
	/**
	 * 
	 */
	public void testCount()  {
		PersonService service = new PersonService(getContext());
		Log.i(TAG, service.getCount()+"");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public  void testScrollData() throws Exception {
		PersonService service = new PersonService(getContext());
		//���Ե�ʱ��  �ı�ǰ��� ���� 8 16 �Ǵ���ڼ��� 8 -- 9 ��ʼ  16 --17 ��ʼ
		List<Person> persons = service.getScrollData(0, 8);
		for(Person person : persons)
			Log.i(TAG, person.toString());
	}
	
	/**
	 * 
	 */
	public void testdelete() {
		PersonService service = new PersonService(getContext());
		service.delete(20);
		//���Է��� :ִ�и÷��� Ȼ�� ִ�� ��������  �Ƿ���19��
	}
}
