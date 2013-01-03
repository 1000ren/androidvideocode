package com.fangwei.B;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        Uri uri = Uri.parse("Content://com.fangwei.content.personprovider/person");
        //
        this.getContentResolver().registerContentObserver(uri, true, new PersonContentObserver(new Handler()));
    }
    
    
    private final class PersonContentObserver extends ContentObserver{
    	public PersonContentObserver(Handler handler){
    		super(handler);
    	}

		/**
		 * ¸´Ð´onchange·½·¨
		 */
		public void onChange(boolean selfChange) {
			// select * from person order by id desc limit 1
			ContentResolver resolver = getApplicationContext().getContentResolver();
			Uri uri = Uri.parse("content://com.fangwei.content.personprovider/person");
			Cursor cursor = resolver.query(uri, null, null, null, "id desc limit 1");
			if(cursor.moveToFirst()){
				Log.i("B",cursor.getString(cursor.getColumnIndex("name")));
			}
			cursor.close();
		}
    	
    }
    
    
}