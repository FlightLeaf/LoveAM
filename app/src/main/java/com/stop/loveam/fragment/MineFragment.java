package com.stop.loveam.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.stop.loveam.R;
import com.stop.loveam.activity.EditActivity;
import com.stop.loveam.activity.UserActivity;

import io.flutter.embedding.android.FlutterActivity;

public class MineFragment extends Fragment {

    int selected = -1;

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
            startActivity(FlutterActivity.createDefaultIntent(getActivity()));
        });

        ImageView imageView = view.findViewById(R.id.set_image);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });

        return view;
    }
}