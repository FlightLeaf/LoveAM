package com.stop.loveam.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.fragment.explore.AmendFragment;
import com.stop.loveam.fragment.explore.CalendarFragment;
import com.stop.loveam.fragment.explore.EnglishFragment;
import com.stop.loveam.fragment.explore.FileFragment;
import com.stop.loveam.fragment.explore.HeadFragment;
import com.stop.loveam.fragment.explore.OptimizationFragment;
import com.stop.loveam.fragment.explore.SceneryFragment;
import com.stop.loveam.fragment.explore.StarFragment;
import com.stop.loveam.fragment.explore.WorldFragment;
import com.stop.loveam.utils.ColorUtils;

import java.util.Objects;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        ColorUtils.setStatusBarColor(this, R.color.white);
        try {
            String data = getIntent().getStringExtra("key");
            switch (Objects.requireNonNull(data)){
                case "head":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HeadFragment()).commit();
                    break;
                case "scenery":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SceneryFragment()).commit();
                    break;
                case "star":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StarFragment()).commit();
                    break;
                case "world":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorldFragment()).commit();
                    break;
                case "english":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EnglishFragment()).commit();
                    break;
                case "optimization":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OptimizationFragment()).commit();
                    break;
                case "amend":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AmendFragment()).commit();
                    break;
                case "calendar":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).commit();
                    break;
                case "file":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FileFragment()).commit();
                    break;
            }
        } catch (Exception e) {
            Log.e("ExploreActivity", Objects.requireNonNull(e.getMessage()));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).commit();
        }


    }
}