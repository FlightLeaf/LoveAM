package com.stop.loveam.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.stop.loveam.utils.MqttUtils;

public class MqttMessageService extends Service {

    public MqttMessageService() {
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("id");
            MqttUtils.subscribeToTopic(message);
            Log.d("MICROSERVICE", "Received broadcast: " + message);
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        MqttUtils.connect(this);
        IntentFilter filter = new IntentFilter("follow_message");
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, filter);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MICROSERVICE", "Service started");
        MqttUtils.subscribeToTopic("hello");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MqttUtils.disconnect();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}