package com.stop.loveam.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.stop.loveam.R;
import com.stop.loveam.dao.Impl.UserDaoImpl;
import com.stop.loveam.dao.UserDao;
import com.stop.loveam.entity.UploadResponse;
import com.stop.loveam.entity.User;
import com.stop.loveam.style.ColorTools;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.HttpUtils;
import com.stop.loveam.utils.UploadCallback;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ImageView mUserImage;
    private ImageView mAddImage;
    private EditText mUserName;
    private EditText mUserEmail;
    private EditText mUserCode;
    private Button mBtnGetCode;
    private EditText mUserPassword;
    private Button mBtnRegister;

    String imgPath = "http://114.55.94.213:9888/uploads/d130c4c3-47f0-4b77-b4ef-1213b9869994.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        ColorTools.setStatusBarColor(this, R.color.white);
        initView();

        mAddImage.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                PictureSelector.create(this)
                        .openGallery(SelectMimeType.ofImage())
                        .setImageEngine(GlideEngine.createGlideEngine())
                        .setMaxSelectNum(1)
                        .forResult(new OnResultCallbackListener<>() {
                            @Override
                            public void onResult(ArrayList<LocalMedia> result) {
                                for (LocalMedia media : result) {
                                    String path = media.getRealPath();
                                    cropRawPhoto(Uri.fromFile(new java.io.File(path)));
                                }
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

        mBtnGetCode.setOnClickListener(view -> {
            if (TextUtils.isEmpty(mUserEmail.getText().toString())) {
                Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> {
                    UserDao userDao = new UserDaoImpl();
                    if (userDao.send(mUserEmail.getText().toString())) {
                        runOnUiThread(()-> Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(()-> Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            }
        });

        mBtnRegister.setOnClickListener(view -> {
            if (TextUtils.isEmpty(mUserName.getText().toString())) {
                Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(mUserEmail.getText().toString())) {
                Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(mUserCode.getText().toString())) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            } else if (!Objects.equals(UserDaoImpl.code, mUserCode.getText().toString())) {
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(mUserPassword.getText().toString())) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setName(mUserName.getText().toString());
                user.setEmail(mUserEmail.getText().toString());
                user.setPassword(mUserPassword.getText().toString());
                user.setImage(imgPath);
                user.setLabel("{}");
                new Thread(() -> {
                    if (new UserDaoImpl().register(user)) {
                        runOnUiThread(()-> Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show());
                        finish();
                    } else {
                        runOnUiThread(()-> Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            }
        });

        findViewById(R.id.goToLogin).setOnClickListener(view -> finish());
    }

    public void getResult(String url) {
        imgPath = url;
        Log.d("TAG--RES", "getResult: " + url);
    }


    public void cropRawPhoto(Uri uri) {

        UCrop.Options options = new UCrop.Options();
        // 修改标题栏颜色
        options.setToolbarColor(getResources().getColor(R.color.red));
        // 修改状态栏颜色
        options.setStatusBarColor(getResources().getColor(R.color.red3));
        // 隐藏底部工具
        options.setHideBottomControls(true);
        // 图片格式
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        // 设置图片压缩质量
        options.setCompressionQuality(100);
        // 设置图片压缩质量
        options.setCompressionQuality(100);
        // 圆
        options.setCircleDimmedLayer(true);
        // 不显示网格线
        options.setShowCropGrid(true);
        // 是否能调整裁剪框
        options.setFreeStyleCropEnabled(false);
        File tempFile = getFilesDir();
        File file = new File(tempFile, System.currentTimeMillis() + ".jpg");
        // 设置源uri及目标uri
        UCrop.of(uri, Uri.fromFile(file))
                // 长宽比
                .withAspectRatio(1, 1)
                // 图片大小
                .withMaxResultSize(1000, 1000)
                // 配置参数
                .withOptions(options)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            final Uri resultUri = UCrop.getOutput(data);
            mUserImage.setImageResource(R.drawable.loading);
            // 开启新线程执行图片上传任务
            new Thread(() -> {
                // 使用HttpUtils进行图片上传
                assert resultUri != null;
                HttpUtils.uploadImage(resultUri.getPath(),new UploadCallback() {
                    @Override
                    public void onComplete(String response) {
                        // 请求成功，解析返回的JSON数据
                        Gson gson = new Gson();
                        UploadResponse uploadResponse = gson.fromJson(response, UploadResponse.class);
                        String url = uploadResponse.getUrl();
                        getResult(url);
                        runOnUiThread(
                                ()-> {
                                    mUserImage.setImageURI(resultUri);
                                    Toast.makeText(RegisterActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
                                }
                        );
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                        runOnUiThread(
                                ()-> Toast.makeText(RegisterActivity.this, "头像上传失败", Toast.LENGTH_SHORT).show()
                        );
                        Log.e("TAG", "Upload failed: " + errorMessage);
                    }
                });
            }).start();
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.d("TAG--CropError", "Crop error: " + cropError);
        }
    }

    public void initView() {
        mUserImage = findViewById(R.id.userImage);
        mAddImage = findViewById(R.id.addImage);
        mUserName = findViewById(R.id.userName);
        mUserEmail = findViewById(R.id.userEmail);
        mUserCode = findViewById(R.id.userCode);
        mBtnGetCode = findViewById(R.id.btn_getCode);
        mUserPassword = findViewById(R.id.userPassword);
        mBtnRegister = findViewById(R.id.btn_register);
    }
}