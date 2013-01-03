package com.fangwei.service;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;


public class FileSdService {
	
	//������
	private Context context ;

	public FileSdService(Context context) {
		this.context = context ;
	}

	public void saveToSdCard(String filename,String content) throws Exception {
		//��SD����Ŀ¼
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		//д���ļ�--���������� ��Ҫ������� ---- ���� �������
		FileOutputStream outStream = new FileOutputStream(file);
		outStream.write(content.getBytes());
		outStream.close();
	}
}
