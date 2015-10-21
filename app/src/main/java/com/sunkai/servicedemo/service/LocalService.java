package com.sunkai.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {

    public class SimpleBinder extends Binder {

        public int add(int a, int b) {
            Log.d("Service", String.valueOf(a + b));
            return a + b;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind");
        return new SimpleBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Service", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "onDestroy");
        super.onDestroy();
    }

}