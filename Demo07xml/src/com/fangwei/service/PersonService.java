package com.fangwei.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.fangwei.domain.Person;

public class PersonService {
	/**
	 * ��ȡ����
	 * PULL ������ȡxml�ļ�����
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	//���ڸ÷��� û��ʹ�����Ա ���� ��������Ϊ static
	public static List<Person> getPersons(InputStream xml) throws Exception{
		
		List <Person> persons = null;
		//���������Ϣ ��װ��һ��personjavabean 
		Person person = null;
		//1 �õ�pull������
		XmlPullParser pullParser = Xml.newPullParser();
		//2 Ϊpull����������Ҫ������xml����
		pullParser.setInput(xml,"utf-8");
//		������ԭ��
//		����char[] = {"<xml...>"}�����ַ����飬��ʼ����
//		�ȶ���xml�ļ��ĵ�һ�� ���Ƿ� ���� startdocment���﷨
		//3 ��������һ���ַ� ---
//		���ǽ�������һ�� ֮�� �Ͳ��� ���� ���½���
		int event = pullParser.getEventType();
		//�ж�ֻҪ���ǵ������ĵ��﷨ ����ѭ�� ����next����
		while(event !=XmlPullParser.END_DOCUMENT)
		{
			switch (event){
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
				break;
			
			case XmlPullParser.START_TAG:
				//�жϵ�ǰ�ڵ������ ȡ�õ�ǰ ָ�� ָ�������
				if("person".equals(pullParser.getName())){
					int id = new Integer(pullParser.getAttributeValue(0));
					person = new Person();
					//��ID����person
					person.setId(id);
				}
				if("name".equals(pullParser.getName())){
					String name = pullParser.nextText();
					person.setName(name);
				}
				if("age".equals(pullParser.getName())){
					 int age = new Integer(pullParser.nextText());
					 person.setAge(age);
				}
				break;
			
			//�ڵ� ���������� ��person��ӵ� List������
			case XmlPullParser.END_TAG:
				if("person".equals(pullParser.getName())){
					persons.add(person);
					person = null ;
				}
			}
			event = pullParser.next();
			}
			
			return persons;
	}

	
	/**
	 * 	��������
	 * @param persons ����
	 * @param out �������
	 * @throws Exception
	 */
	public static void save(List<Person> persons,OutputStream out) throws Exception{
		//�õ�ʵ������
		XmlSerializer serializer = Xml.newSerializer();
		//���ݵ��������
		serializer.setOutput(out,"UTF-8");
		//���xml�ĵ�һ��
		serializer.startDocument("UTF-8", true);
		serializer.startTag(null, "persons");
		for(Person person:persons){
			serializer.startTag(null, "person");
			serializer.attribute(null,"id", person.getId().toString());
		
			serializer.startTag(null, "name");
			serializer.text(person.getName());
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(person.getAge().toString());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}
		
		serializer.endTag(null,"persons");
		serializer.endDocument();
		//���⻺��������
		out.flush();
		out.close();
	}


}



