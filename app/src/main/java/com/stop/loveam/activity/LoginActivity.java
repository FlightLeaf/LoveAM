package com.stop.loveam.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.MainActivity;
import com.stop.loveam.R;
import com.stop.loveam.entity.UserInfo;
import com.stop.loveam.utils.UserDbHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtUser;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        //返回到主页面,销毁该页面
        findViewById(R.id.fanhui1).setOnClickListener(view -> finish());
        //匹配对应账号密码才能登录
        mBtnLogin.setOnClickListener(this);
    }
    public void onClick(View v){
        //获取输入的账号密码,存入username与userpassword
        String username = mEtUser.getText().toString();
        String userpassword = mEtPassword.getText().toString();
        //弹出内容
        String ok= "登录成功";
        String fail = "密码有误，请重新登录";
        String fail1 = "请输入账号与密码";
        String fail3 = "该账号未注册";
        Intent intent= null;
        //空的就提示 "请输入账号与密码"，居中显示
        if(TextUtils.isEmpty(username) && TextUtils.isEmpty(userpassword)){
            Toast toastCenter = Toast.makeText(getApplicationContext(),fail1,Toast.LENGTH_SHORT);
            toastCenter.setGravity(Gravity.CENTER,0,0);
            toastCenter.show();
        }else {
            UserInfo login= UserDbHelper.getInstance(LoginActivity.this).login(username);
            if (login!=null){

                if(username.equals(login.getUsername())&&userpassword.equals(login.getPassword())){
                    //toast
                    Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
                    //如果正确就跳转
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    //不正确,弹出登录失败toast
                    //居中显示
                    Toast toastCenter = Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    toastCenter.show();
                }
            }else{
                Toast toastCenter = Toast.makeText(getApplicationContext(),fail3,Toast.LENGTH_SHORT);
                toastCenter.setGravity(Gravity.CENTER,0,0);
                toastCenter.show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}