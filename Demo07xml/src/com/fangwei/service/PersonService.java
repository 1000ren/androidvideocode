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
	 * 获取数据
	 * PULL 解析读取xml文件内容
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	//由于该方法 没有使用其成员 变量 所有申明为 static
	public static List<Person> getPersons(InputStream xml) throws Exception{
		
		List <Person> persons = null;
		//将下面的信息 封装到一个personjavabean 
		Person person = null;
		//1 得到pull解析器
		XmlPullParser pullParser = Xml.newPullParser();
		//2 为pull解析器设置要解析的xml数据
		pullParser.setInput(xml,"utf-8");
//		解析的原理：
//		首先char[] = {"<xml...>"}读到字符数组，开始解析
//		先读到xml文件的第一行 看是否 符合 startdocment的语法
		//3 先让他读一段字符 ---
//		但是解析到第一段 之后 就不会 继续 向下解析
		int event = pullParser.getEventType();
		//判断只要不是到结束文档语法 不断循环 调用next方法
		while(event !=XmlPullParser.END_DOCUMENT)
		{
			switch (event){
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
				break;
			
			case XmlPullParser.START_TAG:
				//判断当前节点的名称 取得当前 指针 指向的名称
				if("person".equals(pullParser.getName())){
					int id = new Integer(pullParser.getAttributeValue(0));
					person = new Person();
					//将ID传个person
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
			
			//节点 不用作处理 将person添加到 List集合中
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
	 * 	保存数据
	 * @param persons 数据
	 * @param out 输出方向
	 * @throws Exception
	 */
	public static void save(List<Person> persons,OutputStream out) throws Exception{
		//得到实例化器
		XmlSerializer serializer = Xml.newSerializer();
		//内容的输出方向
		serializer.setOutput(out,"UTF-8");
		//输出xml的第一行
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
		//避免缓冲输入流
		out.flush();
		out.close();
	}


}



