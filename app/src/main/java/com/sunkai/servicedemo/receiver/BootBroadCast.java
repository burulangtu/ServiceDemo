package com.sunkai.servicedemo.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.sunkai.servicedemo.service.IntentServiceTest;

/**
 * Created by sunkai on 15/10/21.
 */
public class BootBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent mintent) {
        //开机启动服务
        context.startService(new Intent(context, IntentServiceTest.class));
        if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) {
            // 启动完成
            Intent intent = new Intent(context, Alarmreceiver.class);
            intent.setAction("arui.alarm.action");
            PendingIntent sender = PendingIntent.getBroadcast(context, 0,
                    intent, 0);
            long firstime = SystemClock.elapsedRealtime();
            AlarmManager am = (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);

            // 10秒一个周期，不停的发送广播
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
                    10 * 1000, sender);
        }

		/*
         * 开机启动的Activity*
		 * Intent activity=new Intent(context,MyActivity.class);
		 * activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//不加此句会报错。
		 * context.startActivity(activity);
		 */

		/* 开机启动的应用 */
//        Intent appli = context.getPackageManager().getLaunchIntentForPackage("com.test");
//        context.startActivity(appli);
    }

}
