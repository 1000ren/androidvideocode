package com.fangwei.telLlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		if("5556".equals(number)){
			//setResultData(null);//�������ڽ�������еĵ绰����
			setResultData("18601029843");
		}
	}

}
