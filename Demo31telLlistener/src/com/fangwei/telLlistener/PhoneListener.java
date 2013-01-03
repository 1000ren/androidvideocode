package com.fangwei.telLlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		if("5556".equals(number)){
			//setResultData(null);//清除存放于结果对象中的电话号码
			setResultData("18601029843");
		}
	}

}
