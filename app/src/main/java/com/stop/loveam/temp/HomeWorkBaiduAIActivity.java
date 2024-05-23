package com.stop.loveam.temp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.stop.loveam.R;
import com.stop.loveam.baidu.Ingredient;
import com.stop.loveam.entity.UploadResponse;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.HttpUtils;
import com.stop.loveam.utils.UploadCallback;

import java.io.File;
import java.util.ArrayList;

public class HomeWorkBaiduAIActivity extends AppCompatActivity {

    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_baidu_aiactivity);
        imageView = findViewById(R.id.imageLocal);
        textView = findViewById(R.id.textViewRes);
        findViewById(R.id.addImage).setOnClickListener(v -> {
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
                                    //ImageView设置本地图片
                                    File file = new File(path);
                                    imageView.setImageURI(Uri.fromFile(file));
                                    new Thread(() -> {
                                        String ingredient = Ingredient.ingredient(path);
                                        runOnUiThread(() -> textView.setText(ingredient));
                                    }).start();
                                }
                            }
                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
    }
}