package com.stop.loveam.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.stop.loveam.inter.UploadCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils  {
    private static final String TAG = "---HttpUtils---";

    /**
     * 实现上传图片并返回图床链接
     *
     * @param imagePath 图片路径
     * @param callback  上传结果回调
     * */
    public static void uploadImage(String imagePath, final UploadCallback callback) {
        OkHttpClient client = new OkHttpClient();
        // 创建 RequestBody，用于封装请求参数
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "image.jpg",
                        RequestBody.create(MediaType.parse("image/*"), new File(imagePath)))
                .build();

        // 发送post请求上传图片
        Request request = new Request.Builder()
                .url("http://114.55.94.213:9888/upload")
                .post(requestBody)
                .build();

        // 上传回调
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "Upload failed: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    callback.onComplete(response.body().string());
                } else {
                    assert response.body() != null;
                    callback.onFailure("Upload failed: " + response.body().string());
                }
            }
        });
    }
}
