package com.sunkai.servicedemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sunkai.servicedemo.service.RemoteServiceAidl;

/**
 * Created by sunkai on 15/10/21.
 */
public class Alarmreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("arui.alarm.action")) {
            Intent i = new Intent();
            i.setClass(context, RemoteServiceAidl.class);
            context.startService(i);
        }
    }
}