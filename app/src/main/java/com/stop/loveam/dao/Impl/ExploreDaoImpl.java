package com.stop.loveam.dao.Impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.stop.loveam.dao.ExploreDao;
import com.stop.loveam.entity.EnglishShortSentences;
import com.stop.loveam.entity.RESTCalendar;
import com.stop.loveam.entity.UnderstandingTheWorld;
import com.stop.loveam.entity.weather.Weather;
import com.stop.loveam.utils.DownloadPhotoUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExploreDaoImpl implements ExploreDao {

    private static final String tag = "ExploreDaoImpl";

    private static final MediaType JSONMediaType = MediaType.parse("application/json; charset=utf-8");

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
                    return com.alibaba.fastjson2.JSON.parseObject(responseData, RESTCalendar.class);
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
     * ● 随机风景图片：<a href="https://api.btstu.cn/sjbz/api.php?lx=fengjing&format=json">...</a>
     */
    @Override
    public List<String> getRandomPictures(Context context) {
        //{
        //  "code": "200",
        //  "imgurl": "https://img.btstu.cn/api/images/5cbed13653ce3.jpg",
        //  "width": "1920",
        //  "height": "1080"
        //}
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建一个包含3个线程的线程池

        List<Callable<String>> callables = getCallables(context);

        try {
            // 使用invokeAll方法同时执行所有任务
            List<Future<String>> futures = executorService.invokeAll(callables);

            List<String> imgUrls = new ArrayList<>();
            for (Future<String> future : futures) {
                String imgUrl = future.get(); // 获取每个任务的结果
                if (imgUrl != null) {
                    imgUrls.add(imgUrl);
                }
            }
            return imgUrls; // 返回所有有效的图片URL

        } catch (InterruptedException | ExecutionException e) {
            Log.e(tag, "Error executing tasks", e);
            return null;
        } finally {
            executorService.shutdown(); // 关闭线程池
        }
    }

    @NonNull
    private List<Callable<String>> getCallables(Context context) {
        List<Callable<String>> callables = new ArrayList<>();

        // 创建三个任务并添加到任务列表中
        for (int i = 0; i < 3; i++) {
            callables.add(() -> {
                Request request = new Request.Builder()
                        .url("https://api.btstu.cn/sjbz/api.php?lx=fengjing&format=json")
                        .get()
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseData = response.body().string();
                        Log.d(tag, responseData);
                        JSONObject jsonObject = JSON.parseObject(responseData);
                        // 获取应用的缓存目录
                        File cacheDir = context.getApplicationContext().getExternalCacheDir();
                        assert cacheDir != null;
                        Path path = DownloadPhotoUtil.downloadPicture(jsonObject.get("imgurl").toString(), cacheDir.toPath());
                        Log.d(tag, "path: " + path);
                        return path.toString();
                    } else {
                        Log.e(tag, "getRandomPicture failed, response code: " + response.code());
                        return null;
                    }
                } catch (IOException e) {
                    Log.e(tag, "getRandomPicture failed", e);
                    Toast.makeText(context, "获取图片失败", Toast.LENGTH_SHORT).show();
                    return null;
                }
            });
        }
        return callables;
    }

    /**
     * ● 头像：<a href="https://api.vvhan.com/api/avatar/rand?type=json">...</a>
     */
    @Override
    public String getAvatar() {
        //{
        //  "success": true,
        //  "type": "精选头像",
        //  "url": "https://bucket.4ce.cn/storage/v1/b68507249728af66d212b56ef43d700d.webp"
        //}
        return null;
    }

    /**
     * ● 男生头像：<a href="https://api.vvhan.com/api/avatar/boy?type=json">...</a>
     */
    @Override
    public String getBoyAvatar() {
        //{
        //  "success": true,
        //  "type": "男生头像",
        //  "url": "https://bucket.4ce.cn/storage/v1/0df8c13ae890af08abb00b011aae6afd.webp"
        //}
        return null;
    }

    /**
     * ● 女生头像：<a href="https://api.vvhan.com/api/avatar/girl?type=json">...</a>
     */
    @Override
    public String getGirlAvatar() {
        //{
        //  "success": true,
        //  "type": "女生头像",
        //  "url": "https://bucket.4ce.cn/storage/v1/fbfd6723de13fc8b06198cef98422e66.webp"
        //}
        return null;
    }

    /**
     * ● 60秒读懂世界 <a href="https://api.vvhan.com/api/60s">...</a>
     */
    @Override
    public UnderstandingTheWorld get60s() {
        //{
        //  "success": true,
        //  "title": "在这里每天60秒读懂世界",
        //  "banner": "https://picx.zhimg.com/v2-f098fd946aa8ecebaceaa3e946301990_720w.jpg?source=d16d100b",
        //  "time": "06月05日",
        //  "data": [
        //    "民航局：全面提升外籍来华人员、老年人等出行支付便利化水平。",
        //    ……,
        //    "联合国20多名专家呼吁所有国家承认巴勒斯坦国。",
        //    "【微语】真正的光明决不是永没有黑暗的时间，只是永不被黑暗所掩蔽罢了。真正的英雄决不是永没有卑下的情操，只是永不被卑下的情操所屈服罢了。"
        //  ]
        //}
        return null;
    }

    /**
     * ● 英语短句：<a href="https://api.vvhan.com/api/dailyEnglish?type=sj">...</a>
     */
    @Override
    public EnglishShortSentences getEnglishSentence() {
        //{
        //  "success": true,
        //  "data": {
        //    "zh": "我要像你一样就好了。",
        //    "en": "I wish I could be more like you.",
        //    "pic": "https://staticedu-wps.cache.iciba.com/image/bb0e19e697d15dcf3bacfcdf0f695125.jpg"
        //  }
        //}
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
     */
    @Override
    public Weather getWeather(String city) {
        //{
        //  "success": true,
        //  "city": "北京市",
        //  "data": {
        //    "date": "2024-06-05",
        //    "week": "星期三",
        //    "type": "雷阵雨",
        //    "low": "21°C",
        //    "high": "29°C",
        //    "fengxiang": "西南风",
        //    "fengli": "1-3级",
        //    "night": {
        //      "type": "阴",
        //      "fengxiang": "南风",
        //      "fengli": "1-3级"
        //    }
        //  },
        //  "air": {
        //    "aqi": 64,
        //    "aqi_level": 2,
        //    "aqi_name": "良",
        //    "co": "1",
        //    "no2": "24",
        //    "o3": "115",
        //    "pm10": "77",
        //    "pm2.5": "37",
        //    "so2": "8"
        //  },
        //  "tip": "现在的温度比较舒适~"
        //}
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/weather?city="+city) // 替换为你的服务端地址
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if(response.body() != null){
                    String responseData = response.body().string(); // 将响应体转换为字符串
                    Log.d(tag, responseData); // 日志记录响应数据
                    return com.alibaba.fastjson2.JSON.parseObject(responseData, Weather.class);
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
                    JSONObject jsonObject = JSON.parseObject(responseData);
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
