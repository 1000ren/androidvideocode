package your.fangwei.A;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Intents.Insert;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * 在A应用插入数据
     * @param v
     */
    public void insert(View v) {
    	ContentResolver resolver = this.getContentResolver();
		Uri uri = Uri.parse("content://com.fangwei.content.personprovider/person");
		ContentValues values = new ContentValues();
		values.put("name", "xiaoming");
		values.put("phone", "15827241250");
		values.put("amount", "1000");
		resolver.insert(uri, values);
    }
    
}