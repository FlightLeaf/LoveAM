package com.stop.loveam.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.stop.loveam.activity.SettingsActivity;
import com.stop.loveam.activity.UserActivity;
import com.stop.loveam.dao.Impl.NewsDaoImpl;
import com.stop.loveam.dao.NewsDao;
import com.stop.loveam.temp.HomeWorkLocationActivity;

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

        Button button3 = view.findViewById(R.id.buttonGo3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivity(intent);
        });

        Button button4 = view.findViewById(R.id.buttonGo4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });

        ImageView imageView = view.findViewById(R.id.set_image);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
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
            editor.putBoolean("isLogin", false);
            editor.apply();
        });

        view.findViewById(R.id.buttonGo10).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HomeWorkLocationActivity.class);
            startActivity(intent);
        });

        return view;
    }
}