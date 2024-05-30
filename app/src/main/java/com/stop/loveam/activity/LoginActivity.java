package com.stop.loveam.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.dao.Impl.UserDaoImpl;
import com.stop.loveam.dao.UserDao;
import com.stop.loveam.entity.User;
import com.stop.loveam.style.ColorTools;
import com.stop.loveam.utils.UserDbEngine;


public class LoginActivity extends AppCompatActivity {

    private EditText mEtUser;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ColorTools.setStatusBarColor(this, R.color.white);
        //获取mSharedPreferences
        SharedPreferences mSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        //找到控件
        Button mBtnLogin = findViewById(R.id.btn_login);
        Button mBtnRegister = findViewById(R.id.btn_register);
        mEtUser = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);
        //实现直接跳转注册页面
        mBtnRegister.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.fanhui1).setOnClickListener(view -> finish());

        findViewById(R.id.btn_login).setOnClickListener(view -> {
            if (!TextUtils.isEmpty(mEtUser.getText().toString()) && !TextUtils.isEmpty(mEtPassword.getText().toString())){
                new Thread(() -> {
                    UserDao userDao = new UserDaoImpl();
                    if (userDao.login(mEtUser.getText().toString(), mEtPassword.getText().toString())){

                        try {
                            User user = userDao.get_user_info(mEtUser.getText().toString());
                            UserDbEngine.getInstance(this).addUser(user);
                        } catch (Exception e) {
                            Log.d("LoginActivity", "onCreate: " + e);
                        } finally {
                            SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("isLogin", true);
                            editor.apply();
                            runOnUiThread(() -> {
                                Toast toastCenter = Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT);
                                toastCenter.setGravity(Gravity.CENTER,0,0);
                                toastCenter.show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                // 添加标志，清除顶部的活动
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            });
                        }
                    }
                }).start();
            }
        });
    }
}