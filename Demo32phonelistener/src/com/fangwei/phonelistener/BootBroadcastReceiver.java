package com.fangwei.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, PhoneService.class);
		context.startService(service);
	}

}
