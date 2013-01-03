package com.fangwei.service;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;


public class FileSdService {
	
	//上下文
	private Context context ;

	public FileSdService(Context context) {
		this.context = context ;
	}

	public void saveToSdCard(String filename,String content) throws Exception {
		//打开SD卡的目录
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		//写入文件--相对软件本身 是要东西输出 ---- 就是 用输出流
		FileOutputStream outStream = new FileOutputStream(file);
		outStream.write(content.getBytes());
		outStream.close();
	}
}
