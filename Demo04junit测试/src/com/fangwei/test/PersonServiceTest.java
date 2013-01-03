package com.fangwei.test;

import junit.framework.Assert;
import com.fangwei.service.PersonService;
import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	public void testsave() throws Exception {
		PersonService service = new PersonService();
		service.save();//检查方法的执行是否正常
//		测试的结果       红色  原因：报空引用的 
	}
	
	public void testAdd() throws Exception {
		PersonService service = new PersonService();
		int result = service.add(1, 2);
		//断言 （期待值，返回结果） ----用于比对 期待值 和 真实的返回值
		Assert.assertEquals(3, result);
		//打开我们的大纲视图 Outline
		//一定要选择androidTest
	}
}
