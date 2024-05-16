package com.stop.loveam.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.stop.loveam.R;
import com.stop.loveam.activity.EditActivity;
import com.stop.loveam.activity.UserActivity;

public class MineFragment extends Fragment {


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
            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示")
                    .setIcon(R.drawable.book)
                    .setMessage("确定退出吗？")
                    .setPositiveButton("确定", (dialog1, which) -> {
                    })
                    .setNegativeButton("取消", (dialog2, which) -> {
                    });
            alertDialog = builder.create();
            alertDialog.show();
        });

        Button button3 = view.findViewById(R.id.buttonGo3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivity(intent);
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button4 = view.findViewById(R.id.buttonGo4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });

        return view;
    }
}