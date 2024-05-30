package com.stop.loveam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.activity.HomeActivity;
import com.stop.loveam.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSkipClick(View view) {
        Intent intent = null;
        intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    public void onLoginClick(View view) {
        Intent intent = null;
        intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sp.getBoolean("isLogin", false)){
            Intent intent = null;
            intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}