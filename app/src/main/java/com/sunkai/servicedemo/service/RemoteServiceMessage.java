package com.sunkai.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class RemoteServiceMessage extends Service {


    public static final int GET_RESULT = 1;

    private Messenger mMessenger = new Messenger(new Handler() {
        private int remoteInt = 1;//返回到进程A的值

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GET_RESULT) {
                try {
                    Log.d("Service", "handleMessage");
                    msg.replyTo.send(Message.obtain(null, GET_RESULT, remoteInt++, 0));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                super.handleMessage(msg);
            }
        }
    });

    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind");
        return mMessenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "onDestroy");
        super.onDestroy();
    }

}