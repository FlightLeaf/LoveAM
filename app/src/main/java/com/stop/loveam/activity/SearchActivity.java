package com.stop.loveam.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.utils.ColorUtils;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorUtils.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.activity_search);
    }
}