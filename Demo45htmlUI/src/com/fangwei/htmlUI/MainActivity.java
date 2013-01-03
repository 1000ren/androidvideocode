package com.fangwei.htmlUI;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.fangwei.domain.Contact;
import com.fangwei.service.ContactService;

public class MainActivity extends Activity {
	 private WebView webView;
	    private ContactService contactService;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        webView = (WebView) this.findViewById(R.id.webView);
	        webView.loadUrl("file:///android_asset/index.html");
	        webView.getSettings().setJavaScriptEnabled(true);
	        //添加一个自定义的js对象
	        webView.addJavascriptInterface(new JSObject(), "contact");
	        contactService = new ContactService();
	    }
	    
	    private final class JSObject{
	    	
	    	//赋予点击 名单 后 实现打电话功能
	    	public void call(String phone){
	    		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phone));
	    		startActivity(intent);
	    	}
	    	
	    	public void showcontacts(){
	    		//  [{name:"xxx",amount:600,phone:"13988888"},
	    		// {name:"bb",amount:200,phone:"1398788"}]
	    		try {
					List<Contact> contacts = contactService.getContacts();
					JSONArray jsonArray = new JSONArray();
					for(Contact c : contacts){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("name", c.getName());
						jsonObject.put("amount", c.getAmount());
						jsonObject.put("phone", c.getPhone());
						jsonArray.put(jsonObject);
					}
					String json = jsonArray.toString();
					webView.loadUrl("javascript:show('"+ json+ "')");
				} catch (JSONException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}