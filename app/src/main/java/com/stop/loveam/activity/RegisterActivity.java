package com.stop.loveam.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;
import com.stop.loveam.utils.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //找到控件
        et_username =findViewById(R.id.etzhuce_1);
        et_password = findViewById(R.id.etzhuce_2);

        //返回到登录页面,销毁该页面
        findViewById(R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击注册
        findViewById(R.id.btn_queren).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"请输入账号与密码",Toast.LENGTH_SHORT).show();
                }else {
                    String nickname = null;

                    int row= UserDbHelper.getInstance(RegisterActivity.this).register(username,password,nickname);
                    if (row>0){
                        Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}