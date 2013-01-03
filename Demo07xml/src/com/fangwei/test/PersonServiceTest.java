package com.fangwei.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.domain.Person;
import com.fangwei.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	 private static final String TAG = "personServiceTest";
	//测试方法 对外声明 抛出例外
	public void testPersons() throws Exception {
		InputStream xml = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		//person实体的集合
		List<Person> persons = PersonService.getPersons(xml);
		for(Person person : persons){
			//为了打印方便 在person里面重写person类的Tostring方法
			Log.i(TAG,person.toString());
		}
	}
	
	public void testSave() throws Exception {
		//面向对象的方式
		List<Person> persons = new ArrayList<Person>();
		//为了测试方便  在Person内中添加构造器 
		persons.add(new Person(23,"f",1));
		persons.add(new Person(24,"a",2));
		persons.add(new Person(25,"n",3));
		persons.add(new Person(26,"g",4));
		//<包>/files
		File xmlFile = new File(getContext().getFilesDir(),"fangwei.xml");
		FileOutputStream outStream = new FileOutputStream(xmlFile);
		PersonService.save(persons,outStream);
		
	}
}
