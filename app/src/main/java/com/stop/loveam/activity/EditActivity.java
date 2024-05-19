package com.stop.loveam.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageButton;
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
import com.stop.loveam.entity.UploadResponse;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.HttpUtils;
import com.stop.loveam.utils.UploadCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import jp.wasabeef.richeditor.RichEditor;
import yuku.ambilwarna.AmbilWarnaDialog;

public class EditActivity extends AppCompatActivity {

    private RichEditor mEditor;

    // 创建一个颜色选择器对话框
    int initialColor = Color.BLACK; // 初始颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mEditor = findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("点击即可输入……");

        findViewById(R.id.undo).setOnClickListener(v -> mEditor.undo());
        findViewById(R.id.redo).setOnClickListener(v -> mEditor.redo());
        findViewById(R.id.bold).setOnClickListener(v -> mEditor.setBold());
        findViewById(R.id.italic).setOnClickListener(v -> mEditor.setItalic());
        findViewById(R.id.underline).setOnClickListener(v -> mEditor.setUnderline());
        findViewById(R.id.strikethrough).setOnClickListener(v -> mEditor.setStrikeThrough());
        ImageButton colorButton = findViewById(R.id.color);
        colorButton.setOnClickListener(v -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    ColorStateList tint = ColorStateList.valueOf(color);
                    colorButton.setImageTintList(tint);
                    initialColor = color;
                    mEditor.setTextColor(color);
                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    // 用户取消颜色选择，不做任何操作
                }
            });
            colorPickerDialog.show();
        });
        findViewById(R.id.heading1).setOnClickListener(v -> mEditor.setHeading(1));
        findViewById(R.id.heading2).setOnClickListener(v -> mEditor.setHeading(2));
        findViewById(R.id.heading3).setOnClickListener(v -> mEditor.setHeading(3));

        findViewById(R.id.bullet).setOnClickListener(v -> mEditor.setBullets());
        findViewById(R.id.number).setOnClickListener(v -> mEditor.setNumbers());
        findViewById(R.id.indent).setOnClickListener(v -> mEditor.setIndent());
        findViewById(R.id.outdent).setOnClickListener(v -> mEditor.setOutdent());

        findViewById(R.id.underline).setOnClickListener(v -> mEditor.setUnderline());

        findViewById(R.id.size).setOnClickListener(v -> mEditor.setFontSize(22));
        findViewById(R.id.left).setOnClickListener(v -> mEditor.setAlignLeft());
        findViewById(R.id.center).setOnClickListener(v -> mEditor.setAlignCenter());
        findViewById(R.id.right).setOnClickListener(v -> mEditor.setAlignRight());
        findViewById(R.id.pic).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                PictureSelector.create(this)
                        .openGallery(SelectMimeType.ofImage())
                        .setImageEngine(GlideEngine.createGlideEngine())
                        .forResult(new OnResultCallbackListener<>() {
                            @Override
                            public void onResult(ArrayList<LocalMedia> result) {
                                for (LocalMedia media : result) {
                                    String path = media.getRealPath();
                                    // 开启新线程执行图片上传任务
                                    new Thread(() -> {
                                        // 使用HttpUtils进行图片上传
                                        HttpUtils.uploadImage(path,new UploadCallback() {
                                            @Override
                                            public void onComplete(String response) {
                                                // 请求成功，解析返回的JSON数据
                                                Gson gson = new Gson();
                                                UploadResponse uploadResponse = gson.fromJson(response, UploadResponse.class);
                                                String url = uploadResponse.getUrl();
                                                // 在日志中记录上传成功的消息，并在UI线程中插入图片
                                                Log.d("TAG", "Upload success: " + url);
                                                runOnUiThread(() -> mEditor.insertImage(url, "img", 240));
                                            }

                                            @Override
                                            public void onFailure(String errorMessage) {
                                                // 请求失败，记录错误信息
                                                Log.e("TAG", "Upload failed: " + errorMessage);
                                            }
                                        });
                                    }).start();
                                }
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

        findViewById(R.id.save).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

            if (mEditor.getHtml() != null) {
                String htmlContent = mEditor.getHtml();
                Log.d("TAG", "save: " + htmlContent);

                // 获取外部存储的公共目录
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file = new File(externalStoragePublicDirectory, "news_temp.html");

                try {
                    // 如果文件不存在，创建新文件
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    // 开始写文件
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(htmlContent.getBytes());
                    fileOutputStream.close();

                    // 弹出消息提示保存成功
                    Toast.makeText(this, "文件保存成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e("TAG", "save: " + e.getMessage());
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}