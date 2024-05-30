package com.stop.loveam.temp;

import static androidx.camera.core.CameraEffect.VIDEO_CAPTURE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;

public class HomeWorkVideoActivity extends AppCompatActivity {


    VideoView videoView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_video);

        Button video = findViewById(R.id.video);
        videoView = findViewById(R.id.videoView);
        //点击按钮录制视频
        video.setOnClickListener(v -> {
            //点击按钮录制视频
            Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,10485760L);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
            startActivityForResult(intent,VIDEO_CAPTURE);
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Uri videoUri = data.getData();
                if (videoUri != null) {
                    // 使用ContentResolver获取视频的真实路径或者直接使用Uri
                    videoView.setVideoURI(videoUri);
                    videoView.start();
                    Log.d("videoPath", videoUri.toString());
                }
            } else if (resultCode == RESULT_CANCELED) {
                // 取消
            } else {
                //错误
            }
        }
    }
}