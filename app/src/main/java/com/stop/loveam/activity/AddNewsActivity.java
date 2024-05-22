package com.stop.loveam.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stop.loveam.R;
import com.stop.loveam.compose.CardListKt;
import com.stop.loveam.fragment.ChatAIFragment;
import com.stop.loveam.fragment.EditFragment;
import com.stop.loveam.fragment.HomeFragment;
import com.stop.loveam.fragment.MineFragment;
import com.stop.loveam.fragment.NewsMessageEditFragment;
import com.stop.loveam.fragment.VideoFragment;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.Menu;
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor;

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