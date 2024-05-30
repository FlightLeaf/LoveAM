package com.stop.loveam.temp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HomeWorkRecordActivity extends AppCompatActivity
        implements View.OnClickListener, ServiceConnection{
    ProgressBar pb;
    private int mTimeCount; // 时间计数
    File file;
    TextView timer_text;
    TextView recordRes;
    Button playBtnByService;
    Button closeBtnByService;
    Button buttonData;
    HomeWorkFirstService.DataBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        pb = findViewById(R.id.pb_record);
        findViewById(R.id.recordBtn).setOnClickListener(this);
        findViewById(R.id.playBtn).setOnClickListener(this);
        timer_text = findViewById(R.id.timer);
        recordRes = findViewById(R.id.recordRes);
        playBtnByService = findViewById(R.id.playBtnByService);
        closeBtnByService = findViewById(R.id.closeBtnByService);
        recordRes.setText("请先录制");
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        Intent intent = new Intent(HomeWorkRecordActivity.this, HomeWorkFirstService.class);

        playBtnByService.setOnClickListener(v -> {
            Toast.makeText(this, "开启播放服务", Toast.LENGTH_SHORT).show();
            bindService(intent, this, BIND_AUTO_CREATE);
        });

        closeBtnByService.setOnClickListener(v->{
            if (binder != null){
                Toast.makeText(HomeWorkRecordActivity.this, "停止播放服务", Toast.LENGTH_SHORT).show();
                unbindService(this);
            }
        });

        buttonData = findViewById(R.id.buttonData);
        buttonData.setOnClickListener(v->{
            if (binder != null){
                binder.getService().setUrl("file.getAbsolutePath()");
            } else {
                Toast.makeText(HomeWorkRecordActivity.this, "服务未开启", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View v) {
        if (v.getId() == R.id.recordBtn) {
            file = new File(getExternalCacheDir(), "a2.amr");
            System.out.println(getExternalCacheDir());
            try {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MediaRecorder mMediaRecorder = new MediaRecorder(); // 创建一个媒体录制器
            mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener(){
                public void onError(MediaRecorder mr, int what, int extra) {
                    if (mr != null) {
                        mr.reset(); // 重置媒体录制器
                    }
                }
            }); // 设置媒体录制器的错误监听器
            mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED
                            || what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED) {
                        mr.release();
                    }
                }
            }); // 设置媒体录制器的信息监听器
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 设置音频源为麦克风
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB); // 设置媒体的输出格式
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // 设置媒体的音频编码器
            mMediaRecorder.setMaxDuration(10 * 1000); // 设置媒体的最大录制时长
            mMediaRecorder.setOutputFile(file.getPath()); // 设置媒体文件的保存路径
            try {

                mMediaRecorder.prepare(); // 媒体录制器准备就绪
                mMediaRecorder.start(); // 媒体录制器开始录制

                mTimeCount = 0; // 时间计数清零
                pb.setProgress(mTimeCount);
                Timer mTimer = new Timer(); // 创建一个计时器
                pb.setMax(10);
                // 计时器每隔一秒就更新进度条上的录制进度
                mTimer.schedule(new TimerTask() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        pb.setProgress(mTimeCount++);
                        runOnUiThread(() -> {
                            if (mTimeCount < 10){
                                timer_text.setText("00:0"+mTimeCount);
                            }else if(mTimeCount == 10){
                                timer_text.setText("00:"+mTimeCount);
                            } else {
                                Toast.makeText(HomeWorkRecordActivity.this, "录制完成", Toast.LENGTH_SHORT).show();
                                mTimeCount = 0;
                                timer_text.setText("00:0"+mTimeCount);
                                pb.setProgress(mTimeCount);
                                File file_temp = new File(getExternalCacheDir(), "a2.amr");
                                recordRes.setText("录音结果："+file_temp.getPath());
                                mTimer.cancel();
                            }
                        });
                    }
                }, 0, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (v.getId() == R.id.playBtn) {
            file = new File(getExternalCacheDir(), "a2.amr");
            if (file.exists()){
                System.out.println(getExternalCacheDir());
                MediaPlayer mMediaPlayer = new MediaPlayer();
                mMediaPlayer.reset(); // 重置媒体播放器
                mMediaPlayer.setVolume(0.5f, 0.5f); // 设置音量，可选
                // 设置音频流的类型为音乐
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try{
                    mMediaPlayer.setDataSource(file.toString());
                    mMediaPlayer.prepare(); // 媒体播放器准备就绪
                    mMediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (HomeWorkFirstService.DataBinder) service;
        Log.d("onServiceConnected", "onServiceConnected: "+name.toString());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("onServiceDisconnected", "onServiceDisconnected: "+name.toString());
    }
}
