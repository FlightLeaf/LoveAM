package com.stop.loveam.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DownloadPhotoUtil {

    @SuppressLint("StaticFieldLeak")
    private static Activity mContext;
    private static final int REQUEST_CODE_SAVE_IMG = 0;

    /**
     * 请求读取sd卡的权限
     */
    public static void requestPermission(Activity context, Bitmap bitmap) {
        mContext = context;
        String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        // 判断有无读写权限
        if (hasPermissions(mContext, mPermissionList)) {
            // 已同意 去保存
            saveImage(context, bitmap);
        } else {
            // 未同意 申请权限
            ActivityCompat.requestPermissions(context, mPermissionList, REQUEST_CODE_SAVE_IMG);
        }
    }

    // 是否有对应权限
    public static boolean hasPermissions(Context context, String... perms) {
        // 判断sdk版本
        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) ==
                    PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }

    // 保存图片
    public static void saveImage(Context context, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        boolean isSaveSuccess;
        isSaveSuccess = saveImageToGallery1(context, bitmap);
        if (isSaveSuccess) {
            Toast.makeText(mContext, "保存图片成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * android 10 以下版本
     */
    public static boolean saveImageToGallery(Context context, Bitmap image) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";

        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // 通过io流的方式来压缩保存图片
            boolean isSuccess = image.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            // 保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            return isSuccess;
        } catch (IOException e) {
            Log.e("--保存图片失败：", Objects.requireNonNull(e.getMessage()));
        }
        return false;
    }

    /**
     * android 10 以上版本
     */
    public static boolean saveImageToGallery1(Context context, Bitmap image) {
        long mImageTime = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat")
        String imageDate = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(mImageTime));
        String SCREENSHOT_FILE_NAME_TEMPLATE = "winetalk_%s.png";//图片名称，以"winetalk"+时间戳命名
        String mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, imageDate);

        final ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES
                + File.separator + "winetalk"); //Environment.DIRECTORY_SCREENSHOTS:截图,图库中显示的文件夹名。"dh"
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, mImageFileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATE_ADDED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_EXPIRES, (mImageTime + DateUtils.DAY_IN_MILLIS) / 1000);
        values.put(MediaStore.MediaColumns.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        final Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {

            assert uri != null;
            try (OutputStream out = resolver.openOutputStream(uri)) {
                assert out != null;
                if (!image.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                    return false;
                }
            }

            values.clear();
            values.put(MediaStore.MediaColumns.IS_PENDING, 0);
            values.putNull(MediaStore.MediaColumns.DATE_EXPIRES);
            resolver.update(uri, values, null, null);
        } catch (IOException e) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                resolver.delete(uri, null);
            }
            return false;
        }
        return true;
    }
}
