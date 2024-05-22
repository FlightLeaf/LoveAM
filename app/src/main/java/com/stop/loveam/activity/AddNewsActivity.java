package com.stop.loveam.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.fragment.NewsMessageEditFragment;

public class AddNewsActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_add_news, new NewsMessageEditFragment())
                .commit();
    }
}