package com.fangwei.test;

import junit.framework.Assert;
import com.fangwei.service.PersonService;
import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	public void testsave() throws Exception {
		PersonService service = new PersonService();
		service.save();//��鷽����ִ���Ƿ�����
//		���ԵĽ��       ��ɫ  ԭ�򣺱������õ� 
	}
	
	public void testAdd() throws Exception {
		PersonService service = new PersonService();
		int result = service.add(1, 2);
		//���� ���ڴ�ֵ�����ؽ���� ----���ڱȶ� �ڴ�ֵ �� ��ʵ�ķ���ֵ
		Assert.assertEquals(3, result);
		//�����ǵĴ����ͼ Outline
		//һ��Ҫѡ��androidTest
	}
}
