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
		//<包>/databases/itcast.db
		
	}
	
	/**
	 * 给测试分页提供数据
	 * @throws Exception
	 */
	public void testSave01() throws Exception {
		PersonService service  = new PersonService(getContext());
		for(int i =1 ; i < 20 ; i++){
			Person person = new Person(1,"fangwei","15827241251");
			service.save(person);
			//在文件夹下看 .db用SQLlite软件 打开 看 是否插入
		}
	
	}
	
	/**
	 * 获取记录
	 * @param id
	 * @throws Exception
	 */
	public void testfind() throws Exception {
		PersonService service  = new PersonService(getContext());
		Person person = service.find(1);
		//重写 person的tostring方法
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
		//测试方法 :执行该方法 然后 执行 测试总数  是否是19条
	}
	
	public void testPayment() {
		PersonService service = new PersonService(getContext());
		service.payment();
	}
	
	
}
