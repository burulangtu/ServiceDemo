package com.sunkai.servicedemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sunkai.servicedemo.service.RemoteServiceAidl;

/**
 * Created by sunkai on 15/10/21.
 */
public class BootBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent mintent) {
        //开机启动服务 设置开启启动之后，service即便被任务管理杀死，也会自动重启
        context.startService(new Intent(context, RemoteServiceAidl.class));
        Log.d("Service", "BootBroadCast");
    }

}
