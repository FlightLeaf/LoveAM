package com.stop.loveam.temp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stop.loveam.R;
import com.stop.loveam.compose.LoadNewsState;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeWorkLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_login);

        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        EditText editText = findViewById(R.id.editTextPhone);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);

        String id = sp.getString("id", "");
        String password = sp.getString("password", "");

        editText.setText(id);
        editTextPassword.setText(password);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button = findViewById(R.id.buttonLogin);
        button.setOnClickListener(v -> {
            editor.putString("id", String.valueOf(editText.getText()));
            editor.putString("password", String.valueOf(editTextPassword.getText()));
            editor.commit();
            Intent intent = new Intent(HomeWorkLoginActivity.this, HomeWorkSelectActivity.class);
            startActivity(intent);
        });

        TextView textView = findViewById(R.id.textViewTime);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        textView.setText(formatter.format(date));
        new Thread(() -> {
            //获取当前时间
            while (true) {
                try {
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat _formatter= new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                    Date _date = new Date(System.currentTimeMillis());
                    runOnUiThread(() -> textView.setText(_formatter.format(_date)));
                    Thread.sleep(1000);
                }catch (Exception e){
                    Log.d("HomeWorkLoginActivity", "Thread sleep error"+e);
                }
            }
        }).start();
    }
}