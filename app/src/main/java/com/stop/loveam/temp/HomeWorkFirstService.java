package com.stop.loveam.temp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class HomeWorkFirstService extends Service {
    public HomeWorkFirstService() {
    }

    MediaPlayer mMediaPlayer;

    String url;

    public class DataBinder extends Binder {
        public HomeWorkFirstService getService() {
            return HomeWorkFirstService.this;
        }
    }

    void setUrl(String url){
        this.url = url;
        Log.d("Service", "callMethod - URL:"+url);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onBind - Start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service", "onBind - Stop");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DataBinder();
    }
}