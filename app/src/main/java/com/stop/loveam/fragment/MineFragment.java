package com.stop.loveam.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.stop.loveam.R;
import com.stop.loveam.activity.EditActivity;
import com.stop.loveam.activity.UserActivity;
import com.stop.loveam.dao.Impl.NewsDaoImpl;
import com.stop.loveam.dao.NewsDao;
import com.stop.loveam.temp.HomeWorkBaiduAIActivity;
import com.stop.loveam.temp.HomeWorkHtmlActivity;
import com.stop.loveam.temp.HomeWorkSQLTestActivity;

import java.io.FileOutputStream;

public class MineFragment extends Fragment {

    int selected = -1;

    NewsDao newsDao = new NewsDaoImpl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        Button button = view.findViewById(R.id.buttonGo1);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.stop.loveam.temp.HomeWorkLoginActivity.class);
            startActivity(intent);
        });

        Button button2 = view.findViewById(R.id.buttonGo2);
        button2.setOnClickListener(v -> {

            // 创建单选选项数组
            final String[] items = new String[] {"荟萃", "玉兰", "唐岛湾", "寝室"};

            // 创建单选对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请选择一个选项")
                    .setSingleChoiceItems(items, -1, (dialog, which) -> {
                        selected = which;
                    })
                    .setPositiveButton("确定", (dialog, whichButton) -> {
                        if (selected != -1){
                            switch (selected) {
                                case 0:
                                    Toast.makeText(getActivity(), "荟萃", Toast.LENGTH_SHORT).show();
                                    boolean[] isChecked = new boolean[4];
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("多选框")
                                            .setMultiChoiceItems(new String[] {"热干面","炒饭","饭","水"},isChecked, (_dialog, which, isChecked1) -> {
                                                isChecked[which] = isChecked1;
                                                if (isChecked1) {
                                                    Log.d("选中", which + "");
                                                } else {
                                                    Log.d("取消", which + "");
                                                }
                                            })
                                            .setPositiveButton("确定", (dialog1, which) -> {
                                                //TextView更新
                                            })
                                            .setNegativeButton("取消", (dialog2, which) -> {})
                                            .show();
                                    break;
                                case 1:
                                    //
                                    Toast.makeText(getActivity(), "玉兰", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    //
                                    Toast.makeText(getActivity(), "唐岛湾", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    //
                                    Toast.makeText(getActivity(), "寝室", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });
        });

        Button button3 = view.findViewById(R.id.buttonGo3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivity(intent);
        });

        Button button4 = view.findViewById(R.id.buttonGo4);
        button4.setOnClickListener(v -> {
//            startActivity(FlutterActivity.createDefaultIntent(getActivity()));
//            new Thread(() -> {
//                PgSQLConn.init();
//                PgSQLConn.addUpdDel("insert into news(id, title, description, imageUrl, date, source, likes, url) values('1', 'title', 'description', 'imageUrl', 'date', 'source', 'likes', 'url')");
//            }).start();
        });

        ImageView imageView = view.findViewById(R.id.set_image);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });

        view.findViewById(R.id.buttonGo5).setOnClickListener(v->{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {

            }
        });

        view.findViewById(R.id.buttonGo6).setOnClickListener(v->{
            String file_name = "hello.txt";
            String current = "Hello 5:20AM";
            try {
                FileOutputStream fos = requireContext().openFileOutput(file_name, Context.MODE_PRIVATE);
                fos.write(current.getBytes());
                fos.close();
                if (requireContext().getFileStreamPath(file_name).exists()){
                    Toast.makeText(requireContext(), "写入成功", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(requireContext(), "写入失败", Toast.LENGTH_SHORT).show();
                Log.d("error", e.toString());
            }
        });

        view.findViewById(R.id.buttonGo7).setOnClickListener(v -> {
            SharedPreferences sp = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", "张三");
            editor.putInt("age", 8);
            editor.commit();
        });

        view.findViewById(R.id.buttonGo8).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HomeWorkSQLTestActivity.class);
            startActivity(intent);
        });

        view.findViewById(R.id.buttonGo9).setOnClickListener(v -> {
//            new Thread(() -> {
//                newsDao.get_news(1);
//            }).start();
            Intent intent = new Intent(getActivity(), HomeWorkBaiduAIActivity.class);
            startActivity(intent);
        });

        return view;
    }
}