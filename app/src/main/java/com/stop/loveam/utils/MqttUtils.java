package com.stop.loveam.utils;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.stop.loveam.activity.SettingsActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;

public class MqttUtils {
    private static final String TAG = "MqttService";
    static String broker = "tcp://159.75.108.178:1883";
    static int qos = 0;
    static MqttClient client;
    static MqttConnectOptions options;
    @SuppressLint("StaticFieldLeak")
    static Context _context;

    public static void connect(Context context) {
        _context = context;
        try {
            client = new MqttClient(broker, "subscribe_client", new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            client.connect(options);
        } catch (Exception e) {
            Log.e(TAG, "MqttService: " + e.getMessage());
        }
    }

    public static void subscribeToTopic(String topic) {
        try {
            // 设置回调
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("topic ==>" + topic);
                    System.out.println("Qos ==>" + message.getQos());
                    System.out.println("message content ==>" + new String(message.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        System.out.println("deliveryComplete::::::==>" + token.getMessage());
                        System.out.println("deliveryComplete::::::==>" + Arrays.toString(token.getTopics()));
                        // TODO token.getMessage() 获取关注的作者的作品信息
                        NotificationUtil notificationUtil = new NotificationUtil(_context);
                        Intent intent = new Intent(_context, SettingsActivity.class);
                        notificationUtil.createNotification(
                                "channel_id", "channel_name",
                                NotificationManager.IMPORTANCE_DEFAULT, Arrays.toString(token.getTopics()),
                                token.getMessage().toString(), intent);
                    } catch (MqttException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            client.connect(options);
            client.subscribe(topic, qos);
        } catch (Exception e) {
            Log.e(TAG, "MqttService: " + e.getMessage());
        }
    }

    public static void publishMessage(String topic, String content) {
        try {
            // 创建消息并设置 QoS
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            // 发布消息
            client.publish(topic, message);
            System.out.println("Message published");
        } catch (MqttException e) {
            Log.e(TAG, "MqttService: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            client.disconnect();
            // 关闭客户端
            client.close();
        } catch (MqttException e) {
            Log.e(TAG, "MqttService: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
