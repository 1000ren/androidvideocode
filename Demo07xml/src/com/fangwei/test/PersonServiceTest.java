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
	//���Է��� �������� �׳�����
	public void testPersons() throws Exception {
		InputStream xml = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		//personʵ��ļ���
		List<Person> persons = PersonService.getPersons(xml);
		for(Person person : persons){
			//Ϊ�˴�ӡ���� ��person������дperson���Tostring����
			Log.i(TAG,person.toString());
		}
	}
	
	public void testSave() throws Exception {
		//�������ķ�ʽ
		List<Person> persons = new ArrayList<Person>();
		//Ϊ�˲��Է���  ��Person������ӹ����� 
		persons.add(new Person(23,"f",1));
		persons.add(new Person(24,"a",2));
		persons.add(new Person(25,"n",3));
		persons.add(new Person(26,"g",4));
		//<��>/files
		File xmlFile = new File(getContext().getFilesDir(),"fangwei.xml");
		FileOutputStream outStream = new FileOutputStream(xmlFile);
		PersonService.save(persons,outStream);
		
	}
}
