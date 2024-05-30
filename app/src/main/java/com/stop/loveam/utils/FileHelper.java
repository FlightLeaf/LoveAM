package com.stop.loveam.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileHelper {

    // 获取临时文件的路径
    public static String getTempFilePath(Context context) {

        File tempFile = null;

        try {
            // 创建或获取临时文件夹
            File tempDir = context.getCacheDir();
            // 创建临时文件
            tempFile = File.createTempFile("temp_", ".tmp", tempDir);
        } catch (Exception e) {
            Log.d("TempFileHelper", "getTempFilePath: " + e.getMessage());
        }
        assert tempFile != null;
        return tempFile.getAbsolutePath();
    }
}

