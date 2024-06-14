package com.stop.loveam.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson2.JSONObject;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.stop.loveam.R;

public class VideosActivity extends AppCompatActivity {

    private StyledPlayerView playerView;

    Player mExoPlayer;
    private TextView mVideoName;
    private TextView mVideoDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        initView();

        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("videos");
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        assert jsonObject != null;
        mVideoName.setText(jsonObject.getString("name"));
        mVideoDes.setText(jsonObject.getString("artist"));
        mExoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(mExoPlayer);
        mExoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(jsonObject.getString("url"))));
        mExoPlayer.prepare();
        mExoPlayer.play();
    }

    private void initView() {
        playerView = findViewById(R.id.video_play_preview);
        mVideoName = findViewById(R.id.video_name);
        mVideoDes = findViewById(R.id.video_des);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mExoPlayer.pause();
        mExoPlayer.stop();
        mExoPlayer.release();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}