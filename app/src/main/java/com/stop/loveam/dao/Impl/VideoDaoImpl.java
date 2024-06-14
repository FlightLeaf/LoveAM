package com.stop.loveam.dao.Impl;

import android.util.Log;

import com.alibaba.fastjson2.JSONObject;
import com.stop.loveam.dao.VideoDao;
import com.stop.loveam.entity.mv.Mv;
import com.stop.loveam.entity.videos.Videos;
import com.stop.loveam.entity.videos.VideosResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VideoDaoImpl implements VideoDao {

    private static final String tag = "VideoDaoImpl";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    String api = "http://117.72.69.162:5008";
    @Override
    public List<Videos> get_video_list() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(api + "/get_video_recommendation") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(responseData);
                VideosResponse videosResponse = jsonObject.to(VideosResponse.class);
                return videosResponse.getVideo_list();
            } else {
                Log.e(tag, "get_news_list failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "get_news_list failed", e);
            return null;
        }
    }

    @Override
    public String get_video_url(String id) {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("http://music.163.com/api/mv/detail?id="+id+"&type=mp4") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                Log.d(tag, responseData); // 日志记录响应数据
                JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(responseData);
                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                System.out.println("------------------------------"+responseData);
                Mv mv = jsonObjectData.to(Mv.class);
                Log.d(tag, mv.getBrs().getThe240());
                return mv.getBrs().getThe720();
            } else {
                Log.e(tag, "get_news_list failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "get_news_list failed", e);
            return null;
        }
    }
}
