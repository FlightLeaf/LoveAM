package com.stop.loveam.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.fragment.ChatAIFragment;
import com.stop.loveam.fragment.HomeFragment;
import com.stop.loveam.fragment.MineFragment;
import com.stop.loveam.fragment.VideoFragment;
import com.stop.loveam.utils.ColorUtils;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.Menu;
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor.Builder;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ColorUtils.setStatusBarColor(this, R.color.white);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout, new HomeFragment())
                .commit();


        ExpandableBottomBar bottomBar = findViewById(R.id.expandable_bottom_bar);
        Menu menu = bottomBar.getMenu();

        menu.add(new Builder(this, R.id.fragment_home, R.drawable.round_home_blue, R.string.home_name, Color.RED).build());
        menu.add(new Builder(this, R.id.fragment_movie, R.drawable.baseline_movie_filter_24, R.string.video_name, Color.RED).build());
        menu.add(new Builder(this, R.id.fragment_ai, R.drawable.ai, R.string.ai_name, Color.RED).build());
        menu.add(new Builder(this, R.id.fragment_message, R.drawable.round_person_blue, R.string.my_name, Color.RED).build());

        bottomBar.setOnItemSelectedListener((view, item, byUser) -> {
            switch (item.getId()){
                case R.id.fragment_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout, new HomeFragment())
                            .commit();
                    break;
                case R.id.fragment_ai:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout, new ChatAIFragment())
                            .commit();
                    break;
                case R.id.fragment_movie:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout, new VideoFragment())
                            .commit();
                    break;
                case R.id.fragment_message:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout, new MineFragment())
                            .commit();
                    break;
            }
            Log.d(TAG, "selected: " + item);
            return null;
        });
    }
}
