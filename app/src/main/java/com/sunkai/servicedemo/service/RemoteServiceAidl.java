package com.sunkai.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.sunkai.servicedemo.IMyService;

public class RemoteServiceAidl extends Service {

    private class MyServiceImpl extends IMyService.Stub {
        @Override
        public String getValue() throws RemoteException {
            return "Hello";
        }
    }

    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind");
        return new MyServiceImpl();
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