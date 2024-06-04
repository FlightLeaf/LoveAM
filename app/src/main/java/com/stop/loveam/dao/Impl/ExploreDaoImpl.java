package com.stop.loveam.dao.Impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stop.loveam.dao.ExploreDao;
import com.stop.loveam.entity.News;
import com.stop.loveam.entity.RESTCalendar;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExploreDaoImpl implements ExploreDao {

    private static final String tag = "ExploreDaoImpl";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    /**
     * ● 摸鱼日历：<a href="https://api.vvhan.com/api/moyu?type=json">...</a>
     */
    @Override
    public RESTCalendar getRESTCalendar() {
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/moyu?type=json") // 替换为你的服务端地址
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if(response.body() != null){
                    String responseData = response.body().string(); // 将响应体转换为字符串
                    Log.d(tag, responseData); // 日志记录响应数据

                    Gson gson = new Gson(); // 创建Gson实例
                    JsonParser parser = new JsonParser(); // 创建JsonParser实例
                    JsonObject jsonObject = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject
                    String newJson = jsonObject.toString(); // 获取新闻列表的JSON字符串
                    Type jsonType = new TypeToken<RESTCalendar>(){}.getType(); // 定义List<News>的类型
                    return gson.fromJson(newJson, jsonType);
                } else {
                    Log.e(tag, "get_news_list failed, response body is null");
                    return null;
                }
            } else {
                Log.e(tag, "get_news_list failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "get_news_list failed", e);
            return null;
        }
    }

    /**
     * ● 随机风景图片：<a href="https://api.vvhan.com/api/wallpaper/views?type=json">...</a>
     */
    @Override
    public String getRandomPicture() {
        //{
        //  "success": true,
        //  "type": "风景",
        //  "url": "https://api.vvhan.com/api/wallpaper/views?type=json"
        //}
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/wallpaper/views?type=json") // 替换为你的服务端地址
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if(response.body() != null){
                    String responseData = response.body().string(); // 将响应体转换为字符串
                    Log.d(tag, responseData); // 日志记录响应数据
                    JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
                    return jsonObject.get("url").getAsString();
                } else {
                    Log.e(tag, "getRandomPicture failed, response body is null");
                    return null;
                }
            } else {
                Log.e(tag, "getRandomPicture failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "getRandomPicture failed", e);
            return null;
        }
    }

    /**
     * ● 头像：<a href="https://api.vvhan.com/api/avatar/rand?type=json">...</a>
     */
    @Override
    public String getAvatar() {
        return null;
    }

    /**
     * ● 男生头像：<a href="https://api.vvhan.com/api/avatar/boy?type=json">...</a>
     */
    @Override
    public String getBoyAvatar() {
        return null;
    }

    /**
     * ● 女生头像：<a href="https://api.vvhan.com/api/avatar/girl?type=json">...</a>
     */
    @Override
    public String getGirlAvatar() {
        return null;
    }

    /**
     * ● 60秒读懂世界https://api.vvhan.com/api/60s
     */
    @Override
    public String get60s() {
        return null;
    }

    /**
     * ● 英语短句：<a href="https://api.vvhan.com/api/dailyEnglish?type=sj">...</a>
     */
    @Override
    public String getEnglishSentence() {
        return null;
    }

    /**
     * ● 星座数据：<a href="https://api.vvhan.com/article/horoscope.html">...</a>
     */
    @Override
    public String getHoroscope() {
        return null;
    }

    /**
     * ● 天气：<a href="https://api.vvhan.com/api/weather?city=北京">...</a>
     *
     * @param city
     */
    @Override
    public String getWeather(String city) {
        return null;
    }

    /**
     * ● 随即一言：<a href="https://zj.v.api.aa1.cn/api/wenan-wm/?type=json">...</a>
     */
    @Override
    public String getRandomSaying() {
        Request request = new Request.Builder()
                .url("https://zj.v.api.aa1.cn/api/wenan-wm/?type=json") // 替换为你的服务端地址
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if(response.body() != null){
                    String responseData = response.body().string(); // 将响应体转换为字符串
                    Log.d(tag, responseData); // 日志记录响应数据

                    Gson gson = new Gson(); // 创建Gson实例
                    JsonParser parser = new JsonParser(); // 创建JsonParser实例
                    JsonObject jsonObject = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject

                    return jsonObject.get("msg").toString();
                } else {
                    Log.e(tag, "get_news_list failed, response body is null");
                    return null;
                }
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
