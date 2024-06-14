package com.stop.loveam.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.dao.Impl.UserDaoImpl;
import com.stop.loveam.dao.UserDao;
import com.stop.loveam.entity.News;
import com.stop.loveam.entity.User;
import com.stop.loveam.utils.ColorUtils;
import com.stop.loveam.utils.GlideEngine;

public class ReadingActivity extends AppCompatActivity {

    private TextView mTitleNews;
    private ImageView mImageUser;
    private TextView mNameUser;
    private Button mFollowUser;
    private TextView descriptionNews;
    private WebView mNewsWebView;
    private EditText mCommentEditText;
    private ImageView mFollowBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        ColorUtils.setStatusBarColor(this, R.color.white);

        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("key");

        initView();
        assert news != null;
        mTitleNews.setText(news.getTitle());
        mNameUser.setText(news.getEmail());
        descriptionNews.setText("发表时间: "+news.getCreatedAt());
        // mNewsWebView.loadUrl("http://ocean.upc.edu.cn/");

        new Thread(() -> {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.get_user_info(news.getEmail());
            if (user != null) {
                runOnUiThread(() -> {
                    mNameUser.setText(user.getName());
                    GlideEngine.createGlideEngine().loadAlbumCover(this, user.getImage(), mImageUser);
                });
            }
        }).start();

        mFollowUser.setOnClickListener(v -> {

        });
    }

    private void initView() {
        mTitleNews = findViewById(R.id.titleNews);
        mImageUser = findViewById(R.id.imageUser);
        mNameUser = findViewById(R.id.nameUser);
        mFollowUser = findViewById(R.id.followUser);
        descriptionNews = findViewById(R.id.descriptionNews);
        // mNewsWebView = findViewById(R.id.news_webView);
        mCommentEditText = findViewById (R.id.comment_editText);
        mFollowBtn = findViewById(R.id.follow_btn);
    }
}