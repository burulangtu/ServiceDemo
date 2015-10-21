package com.sunkai.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunkai.servicedemo.service.IntentServiceTest;
import com.sunkai.servicedemo.service.LocalService;
import com.sunkai.servicedemo.service.RemoteServiceAidl;
import com.sunkai.servicedemo.service.RemoteServiceMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.textView)
    protected TextView log;

    private Messenger mService;
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == RemoteServiceMessage.GET_RESULT) {
                Log.d("Service", "mMessenger: " + msg.arg1);//msg.arg1就是remoteInt
            } else {
                super.handleMessage(msg);
            }
        }
    });
    private ServiceConnection serviceConnection1 = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Service", "mMessenger: onServiceDisconnected");
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service", "mMessenger: onServiceConnected");
            mService = new Messenger(service);
        }
    };

    private IMyService iMyService;
    private ServiceConnection serviceConnection2 = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Service", "iMyService: onServiceDisconnected");
            iMyService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service", "iMyService: onServiceConnected");
            iMyService = IMyService.Stub.asInterface(service);
        }
    };


    LocalService.SimpleBinder sBinder;
    private ServiceConnection serviceConnection3 = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Service", "sBinder: onServiceDisconnected");
            sBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service", "sBinder: onServiceConnected");
            sBinder = (LocalService.SimpleBinder) service;
        }
    };

    private ServiceConnection serviceConnection4 = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Service", "IntentService: onServiceDisconnected");
            sBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service", "IntentService: onServiceConnected");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnBind1, R.id.btnBind2, R.id.btnBind3, R.id.btnBind4, R.id.btnBind5, R.id.btnBind6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBind1:
                bindService(new Intent(MainActivity.this, RemoteServiceMessage.class), serviceConnection1, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnBind2:
                bindService(new Intent(MainActivity.this, RemoteServiceAidl.class), serviceConnection2, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnBind3:
                bindService(new Intent(MainActivity.this, LocalService.class), serviceConnection3, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnBind4:
                startService(new Intent(this, LocalService.class));
                break;
            case R.id.btnBind5:
                startService(new Intent(this, IntentServiceTest.class));
                break;
            case R.id.btnBind6:
                bindService(new Intent(MainActivity.this, LocalService.class), serviceConnection4, Context.BIND_AUTO_CREATE);
                break;
        }
    }

    @OnClick({R.id.btnUnbind1, R.id.btnUnbind2, R.id.btnUnbind3, R.id.btnUnbind4, R.id.btnUnbind5, R.id.btnUnbind6})
    public void unBindService(View view) {
        switch (view.getId()) {
            case R.id.btnUnbind1:
                unbindService(serviceConnection1);
                break;
            case R.id.btnUnbind2:
                unbindService(serviceConnection2);
                break;
            case R.id.btnUnbind3:
                unbindService(serviceConnection3);
                break;
            case R.id.btnUnbind4:
                stopService(new Intent(this, LocalService.class));
                break;
            case R.id.btnUnbind5:
                stopService(new Intent(this, IntentServiceTest.class));
                break;
            case R.id.btnUnbind6:
                unbindService(serviceConnection4);
                break;
        }
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void testService(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (null != mService) {
                    Message message = Message.obtain(null, RemoteServiceMessage.GET_RESULT);
                    message.replyTo = mMessenger;
                    try {
                        mService.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button2:
                if (null != iMyService) {
                    try {
                        iMyService.getValue();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button3:
                if (null != sBinder) {
                    sBinder.add(3, 5);
                }
                break;
        }
    }
}
