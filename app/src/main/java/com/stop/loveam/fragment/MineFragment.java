package com.stop.loveam.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.stop.loveam.R;
import com.stop.loveam.activity.EditActivity;
import com.stop.loveam.activity.SettingsActivity;
import com.stop.loveam.activity.VideosActivity;
import com.stop.loveam.dao.Impl.NewsDaoImpl;
import com.stop.loveam.dao.NewsDao;
import com.stop.loveam.service.MqttMessageService;
import com.stop.loveam.utils.MqttUtils;
import com.stop.loveam.utils.NotificationUtil;

public class MineFragment extends Fragment {

    private static final String CHANNEL_ID = "MineFragment";
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
            Intent intent = new Intent(getActivity(), MqttMessageService.class);
            requireActivity().startService(intent);
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

        view.findViewById(R.id.buttonGo6).setOnClickListener(v -> {
//            String file_name = "hello.txt";
//            String current = "Hello 5:20AM";
//            try {
//                FileOutputStream fos = requireContext().openFileOutput(file_name, Context.MODE_PRIVATE);
//                fos.write(current.getBytes());
//                fos.close();
//                if (requireContext().getFileStreamPath(file_name).exists()){
//                    Toast.makeText(requireContext(), "写入成功", Toast.LENGTH_SHORT).show();
//                }
//            }catch (Exception e){
//                Toast.makeText(requireContext(), "写入失败", Toast.LENGTH_SHORT).show();
//                Log.d("error", e.toString());
//            }
//
//            new Thread(() -> {
//                ExploreDao exploreDao = new ExploreDaoImpl();
//                Weather weather = exploreDao.getWeather("北京");
//            }).start();
//            MqttService mqttService = new MqttService(this);
//            mqttService.connect(); // 连接到MQTT服务器
//            mqttService.publishMessage("Hello MQTT"); // 发布消息
//            mqttService.disconnect(); // 断开连接
//            val sp = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
//            val editor = sp.edit()
//            editor.putString("flutter.int", jsonObject.toString())
//            editor.apply()

            Intent intent = new Intent(getActivity(), VideosActivity.class);
            startActivity(intent);
        });

        view.findViewById(R.id.buttonGo7).setOnClickListener(v -> {
            SharedPreferences sp = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isLogin", false);
            editor.apply();
        });



        view.findViewById(R.id.buttonGo11).setOnClickListener(v -> {

            Intent _intent = new Intent("follow_message");
            _intent.putExtra("id", "test");
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(_intent);

            NotificationUtil notificationUtil = new NotificationUtil(requireContext());
            Intent intent = new Intent(requireActivity(), SettingsActivity.class);
            notificationUtil.createNotification(
                    "channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT, "通知标题",
                    "通知内容", intent);
        });

        view.findViewById(R.id.buttonGo12).setOnClickListener(v -> {
            new Thread(() -> {
                MqttUtils.publishMessage("Hello", "test");
            }).start();
        });

        view.findViewById(R.id.buttonGo13).setOnClickListener(v -> {
            new Thread(() -> {
                MqttUtils.publishMessage("test", "test");
            }).start();
        });

        return view;
    }

}