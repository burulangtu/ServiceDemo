package com.sunkai.servicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by sunkai on 15/10/21.
 */
public class IntentServiceTest extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceTest(String name) {
        super(name);
    }

    public IntentServiceTest() {
        super("IntentServiceTest");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "onHandleIntent");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind");
        return null;
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
