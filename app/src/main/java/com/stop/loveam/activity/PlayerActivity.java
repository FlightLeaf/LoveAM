package com.stop.loveam.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.stop.loveam.R;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);

        PlayerView playerView = findViewById(R.id.video_view);
        playerView.setUseController(false);
        ViewCompat.setOnApplyWindowInsetsListener(playerView, (v, insets) -> {
            Insets insets1 = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            playerView.setPadding(insets1.left, insets1.top, insets1.right, insets1.bottom);
            return insets;
        });

        // 创建ExoPlayer实例
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        String videoUrl = "http://vodkgeyttp8.vod.126.net/cloudmusic/OCIiIzAmYTdgIjBkNjQgIg==/mv/419786/876e30d5b621fb5da64963d90a659c4b.mp4?wsSecret=cfca22bdb7d8c28ccb7f6fad1d68ba4c&wsTime=1715920983";
        Uri videoUri = Uri.parse(videoUrl);

        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        player.setMediaItem(mediaItem);
        player.prepare();

        player.play();

        // 设置播放器准备就绪，一旦可以就会开始播放
        player.setPlayWhenReady(true);

        // 将播放位置重置到开头
        player.seekTo(0);

        // 设置播放速度为正常速度1.0倍
        player.setPlaybackSpeed(1.0f);
        // 设置播放模式为单曲循环
        player.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ONE);
        // 设置音量为最大值
        player.setVolume(1.0f);
        // 设置音频属性，如果为null，则使用默认属性，并设置是否应用媒体的音量
        player.setAudioAttributes(null, true);

        //播放暂停
        player.stop();



    }
}