package com.stop.loveam.fragment;

import static android.app.Activity.RESULT_OK;
import static com.luck.picture.lib.thread.PictureThreadUtils.runOnUiThread;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.stop.loveam.R;
import com.stop.loveam.entity.News;
import com.stop.loveam.entity.UploadResponse;
import com.stop.loveam.inter.UploadCallback;
import com.stop.loveam.sqliteUtils.NewsFileDbEngine;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.HttpUtils;
import com.stop.loveam.utils.LogUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.richeditor.RichEditor;
import yuku.ambilwarna.AmbilWarnaDialog;

public class EditFragment extends Fragment {

    private RichEditor mEditor;
    int initialColor = Color.BLACK; // 初始颜色
    EditText title_text;
    EditText des_text;
    private OnBackPressedCallback onBackPressedCallback;
    private ImageView mNewsImage;
    String imgPath = "http://114.55.94.213:9888/uploads/d130c4c3-47f0-4b77-b4ef-1213b9869994.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 创建AlertDialog.Builder对象并显示对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("确认退出吗？");
                builder.setMessage("您确认已经保存了吗？");
                builder.setPositiveButton("退出", (dialog, which) -> {
                    // 当点击退出按钮时，调用remove()来允许后退事件传递给Activity
                    onBackPressedCallback.remove();
                    // 然后调用Activity的onBackPressed()
                    requireActivity().finish();
                });
                builder.setNegativeButton("取消", (dialog, which) -> {
                    // 当点击取消按钮时，取消对话框
                    dialog.dismiss();
                });
                // 显示对话框
                builder.show();
            }
        };

        // 将OnBackPressedCallback添加到Activity的OnBackPressedDispatcher
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), onBackPressedCallback);

        mEditor = view.findViewById(R.id.editor);
        mEditor.setEditorHeight(266);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("点击即可输入……");

        title_text = view.findViewById(R.id.title_text_news);
        des_text = view.findViewById(R.id.title_text_des);
        view.findViewById(R.id.undo).setOnClickListener(v -> mEditor.undo());
        view.findViewById(R.id.redo).setOnClickListener(v -> mEditor.redo());
        view.findViewById(R.id.bold).setOnClickListener(v -> mEditor.setBold());
        view.findViewById(R.id.italic).setOnClickListener(v -> mEditor.setItalic());
        view.findViewById(R.id.underline).setOnClickListener(v -> mEditor.setUnderline());
        view.findViewById(R.id.strikethrough).setOnClickListener(v -> mEditor.setStrikeThrough());
        ImageButton colorButton = view.findViewById(R.id.color);

        ImageButton mAddNewsImage = view.findViewById(R.id.addNewsImage);
        mNewsImage = view.findViewById(R.id.newsImageHead);

        colorButton.setOnClickListener(v -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(getActivity(), initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
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
        view.findViewById(R.id.heading1).setOnClickListener(v -> mEditor.setHeading(1));
        view.findViewById(R.id.heading2).setOnClickListener(v -> mEditor.setHeading(2));
        view.findViewById(R.id.heading3).setOnClickListener(v -> mEditor.setHeading(3));

        view.findViewById(R.id.bullet).setOnClickListener(v -> mEditor.setBullets());
        view.findViewById(R.id.number).setOnClickListener(v -> mEditor.setNumbers());
        view.findViewById(R.id.indent).setOnClickListener(v -> mEditor.setIndent());
        view.findViewById(R.id.outdent).setOnClickListener(v -> mEditor.setOutdent());

        view.findViewById(R.id.underline).setOnClickListener(v -> mEditor.setUnderline());

        view.findViewById(R.id.size).setOnClickListener(v -> mEditor.setFontSize(22));
        view.findViewById(R.id.left).setOnClickListener(v -> mEditor.setAlignLeft());
        view.findViewById(R.id.center).setOnClickListener(v -> mEditor.setAlignCenter());
        view.findViewById(R.id.right).setOnClickListener(v -> mEditor.setAlignRight());
        view.findViewById(R.id.pic).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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

        view.findViewById(R.id.save_btn).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
            fileSave();
        });

        view.findViewById(R.id.closeButton).setOnClickListener(v -> {
            //弹窗拦截
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle("确认退出吗？");
            builder.setMessage("您确认已经保存了吗？");
            builder.setPositiveButton("退出", (dialog, which) -> {
                onBackPressedCallback.remove();
                requireActivity().finish();
            });
            builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        mAddNewsImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 当Fragment被销毁时，移除OnBackPressedCallback
        onBackPressedCallback.remove();
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
        File tempFile = requireContext().getFilesDir();
        File file = new File(tempFile, System.currentTimeMillis() + ".jpg");
        // 设置源uri及目标uri
        UCrop.of(uri, Uri.fromFile(file))
                // 长宽比
                .withAspectRatio(1, 1)
                // 图片大小
                .withMaxResultSize(1000, 1000)
                // 配置参数
                .withOptions(options)
                .start(requireActivity(), this, UCrop.REQUEST_CROP);
    }

    public void fileSave(){
        if (mEditor.getHtml() != null) {
            String htmlContent = mEditor.getHtml();
            Log.d("TAG", "save: " + htmlContent);

            // 获取外部存储的公共目录
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            LogUtils.d(externalStoragePublicDirectory.getAbsolutePath());
            // 获取时间戳
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            File file = null;
            if(!title_text.getText().toString().isEmpty()){
                String fileName = title_text.getText() + "_" + timestamp + ".html";
                file = new File(externalStoragePublicDirectory, fileName);
            }else {
                String fileName = "news_" + timestamp + ".html";
                file = new File(externalStoragePublicDirectory, fileName);
            }
            String create_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            //插入本地数据库
            News news = new News(
                    title_text.getText().toString(),
                    des_text.getText().toString(),
                    imgPath,
                    create_at,
                    file.getAbsolutePath());

            NewsFileDbEngine.getInstance(requireContext()).addNews(news);
            List<News> newsList = NewsFileDbEngine.getInstance(requireContext()).getNewsList();
            for (News news1 : newsList) {
                Log.d("TAG", "save: " + news1.getTitle());
                NewsFileDbEngine.getInstance(requireContext()).deleteNews(news1.getId());
            }
            try {
                // 开始写文件
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(htmlContent.getBytes());
                fileOutputStream.close();
                // 弹出消息提示保存成功
                Toast.makeText(getActivity(), "文件保存成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e("TAG", "save: " + e.getMessage());
                Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: ");
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            final Uri resultUri = UCrop.getOutput(data);
            mNewsImage.setImageResource(R.drawable.loading_setting);
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
                                    mNewsImage.setImageURI(resultUri);
                                    Toast.makeText(requireContext(), "上传成功", Toast.LENGTH_SHORT).show();
                                }
                        );
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                        runOnUiThread(
                                ()-> Toast.makeText(requireContext(), "上传失败", Toast.LENGTH_SHORT).show()
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

}