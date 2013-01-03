package com.fangwei.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;

public class FileService {
	//������
	private Context context ;
	
	public FileService(Context context) {
		this.context = context ;
	}
	
	/**
	 * �����ļ�  ˽��ģʽ
	 * @param filename �ļ���
	 * @param content �ļ�����
	 * @throws Exception
	 */
	public void save01(String filename,String content) throws Exception {
		//���ļ������в��� ----- ����Android��context������ ��openFileOutput �����ļ�����
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//���ļ����ݽ��в��� 
		outStream.write(content.getBytes());
		//�ر���
		outStream.close();
	}
	/**
	 * �����ļ�  ׷��ģʽ
	 * @param filename �ļ���
	 * @param content �ļ�����
	 * @throws Exception
	 */
	public void save02(String filename,String content) throws Exception {
		//���ļ������в��� ----- ����Android��context������ ��openFileOutput �����ļ�����
		//׷��ģʽ �� ������ �� ���� ��ӵ�ǰ����β
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_APPEND);
		//���ļ����ݽ��в��� 
		outStream.write(content.getBytes());
		//�ر���
		outStream.close();
	}
	/**
	 * �����ļ�  ֻ��ģʽ
	 * @param filename �ļ���
	 * @param content �ļ�����
	 * @throws Exception
	 */
	public void save03(String filename,String content) throws Exception {
		//���ļ������в��� ----- ����Android��context������ ��openFileOutput �����ļ�����
		//ֻ�� ģʽ ��ֻ�ɹ�����Ӧ�� ��
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_READABLE);
		//���ļ����ݽ��в��� 
		outStream.write(content.getBytes());
		//�ر���
		outStream.close();
	}
	/**
	 * �����ļ�  ֻдģʽ
	 * @param filename �ļ���
	 * @param content �ļ�����
	 * @throws Exception
	 */
	public void save04(String filename,String content) throws Exception {
		//���ļ������в��� ----- ����Android��context������ ��openFileOutput �����ļ�����
		// ֻдģʽ��ֻ���Թ�����Ӧ��д��
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_WRITEABLE);
		//���ļ����ݽ��в��� 
		outStream.write(content.getBytes());
		//�ر���
		outStream.close();
	}
	/**
	 * �����ļ�  ��дģʽ
	 * @param filename �ļ���
	 * @param content �ļ�����
	 * @throws Exception
	 */
	public void save05(String filename,String content) throws Exception {
		//���ļ������в��� ----- ����Android��context������ ��openFileOutput �����ļ�����
		// ��дģʽ ���ɹ�����Ӧ�ö�д
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
		//���ļ����ݽ��в��� 
		outStream.write(content.getBytes());
		//�ر���
		outStream.close();
	}
	
	
	/**
	 * ��ȡ�ļ�������
	 * @param filename �ļ�����
	 * @return  �ļ�����
	 * @throws Exception
	 */
	 
	public String read(String filename) throws Exception {
		//���ڴ�д����
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		FileInputStream inStream = context.openFileInput(filename);
		byte [] buffer = new byte[1024];
		int len = 0 ;
		//�ж������Ƿ�������
		while  ((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		outStream.close();
		inStream.close();
		//����� �ڴ��е����� ���� �ֽ� ����
		byte [] data = outStream.toByteArray();
		//��UTF-8ת��Ϊ�ַ���
		return new String(data);
	}
}
