package com.fangwei.providertest;

import android.test.AndroidTestCase;

import com.fangwei.domain.Person;
import com.fangwei.provider.DBOpenHelper;
import com.fangwei.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	
	
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
//		Person person = new Person("9999", "186010233434", 200);
//		service.save(person);
	
	}
}
