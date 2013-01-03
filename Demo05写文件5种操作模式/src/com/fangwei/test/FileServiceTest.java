package com.fangwei.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.service.FileService;

public class FileServiceTest extends AndroidTestCase{
	//��Log����һ�������ı�ǩ
	private static final String TAG = "FileServiceTest" ;
	
	public void testRead() throws Exception{
		//�õ������Ķ���
		FileService service = new FileService(getContext());
		
		//�������Ķ�ȡ�ķ���----��ӡ���Ľ���У�������������� ���ǳ�������� ��ģ��������������
		String result = service.read("nhhh.txt");
		
		//��ӡlog��־
		Log.i(TAG, result);//��ӡ��� 123456
	}
}
