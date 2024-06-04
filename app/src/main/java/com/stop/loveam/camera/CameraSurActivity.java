package com.stop.loveam.camera;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stop.loveam.R;

import java.io.IOException;

public class CameraSurActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_sur);
        mImageView = findViewById(R.id.imageView3);

        Intent intent = getIntent();
        if (intent.getStringExtra("url") != null) {
            String url = intent.getStringExtra("url");
            mImageView.setImageURI(Uri.parse(url));
        }
    }

}