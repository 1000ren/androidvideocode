package com.fangwei.download;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MulThreadDownloader {
	public static void main(String[] args) throws Exception {
		String  path ="http://192.168.1.102:8080/Mulse/QQ.exe";
//												/Mulse/QQ.exe
		int threadsize = 3;
		new MulThreadDownloader().download(path,threadsize);
	}
	
	
	private void download(String path,int threadsize) throws Exception {
		URL downpath = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) downpath.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode() == 200){
			//获取网络文件的长度
			int length = conn.getContentLength();
			File file = new File (getFileName(path));
			//生成本地文件
			RandomAccessFile accessFile = new RandomAccessFile(file,"rwd");
			accessFile.setLength(length);
			accessFile.setLength(length);
			accessFile.close();
			//计算每个线程负责下载的数据量
			int block = length % threadsize ==
					0 ? length / threadsize : length/threadsize+1;
			for (int threadid = 0 ; threadid < threadsize ; threadid++){
				new DownloadThread(threadid,downpath,block,file).start();
			}
		}
	
	}
	
	//负责下载操作
	private final class DownloadThread extends Thread{
		private int threadid;
		private URL downpath;
		private int block;
		private File file;
	
	public DownloadThread(int threadid ,URL downpath,int block, File file){
		this.threadid = threadid;
		this.downpath = downpath;
		this.block= block;
		this.file = file; 
	}
	
	public void run(){
		//从网络文件的什么位置开始下载数据
		int startposition = threadid * block;
		//下载到网络文件的什么位置结束
		int endposition = (threadid+1) * block-1;
		//指示该线程要从网络文件的startposition位置开始下载。下载到endposition
		//位置结束 range:byte = startposition=endposition;
		try {
			RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
			//移动指针到文件某个位置
			accessFile.seek(startposition);
			HttpURLConnection conn = (HttpURLConnection) downpath.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Range", "bytes="+startposition+"-"+endposition);
			InputStream inStream = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0 ;
			while ((len = inStream.read(buffer))!=-1){
				accessFile.write(buffer,0,len);
			}
			accessFile.close();
			inStream.close();
			System.out.println("第"+(threadid+1)+"线程下载完成");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
	
	private static String getFileName(String path) {
		return path.substring(path.lastIndexOf("/")+1);
	}
}
