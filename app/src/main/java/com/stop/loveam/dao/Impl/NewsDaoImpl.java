package com.stop.loveam.dao.Impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stop.loveam.dao.NewsDao;
import com.stop.loveam.entity.FollowUser;
import com.stop.loveam.entity.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewsDaoImpl implements NewsDao {

    private static final String tag = "NewsDaoImpl";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    String api = "http://117.72.69.162:5008";

    @Override
    public boolean add_news(News news) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", news.getTitle());
            jsonObject.put("description", news.getDescription());
            jsonObject.put("imageurl", news.getImageurl());
            jsonObject.put("email", news.getEmail());
            jsonObject.put("url", news.getUrl());
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/add_news") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "add_news failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "add_news failed", e);
            return false;
        }
    }

    @Override
    public boolean delete_news(long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/delete_news") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "delete_news failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "delete_news failed", e);
            return false;
        }
    }

    @Override
    public boolean modify_news(News news) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", news.getId());
            jsonObject.put("title", news.getTitle());
            jsonObject.put("description", news.getDescription());
            jsonObject.put("imageurl", news.getImageurl());
            jsonObject.put("email", news.getEmail());
            jsonObject.put("url", news.getUrl());
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/modify_news") // 替换为你的服务端地址
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "modify_news failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "modify_news failed", e);
            return false;
        }
    }

    @Override
    public List<News> search_news(String keyword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", keyword);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return null;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/search_news") // 替换为你的服务端地址
                .post( body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                Log.d(tag, responseData);
                //TODO 解析JSON数据
                return null;
            } else {
                Log.e(tag, "search_news failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "search_news failed", e);
            return null;
        }
    }

    @Override
    public News get_news(long id) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("id", id);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return null;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/get_news") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                Log.d(tag, responseData);
                //TODO 解析JSON数据
                return null;
            } else {
                Log.e(tag, "get_news failed, response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(tag, "get_news failed", e);
            return null;
        }
    }

    @Override
    public List<News> get_news_list() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(api + "/get_news_list") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                /**
                 * 处理网络响应，解析JSON数据并将其转换为News对象的列表。
                 *
                 * @param response 包含从服务器接收的响应数据的对象。
                 * @param tag 用于日志记录的标签。
                 * @return 无返回值。
                 */
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                Log.d(tag, responseData); // 日志记录响应数据

                Gson gson = new Gson(); // 创建Gson实例
                JsonParser parser = new JsonParser(); // 创建JsonParser实例
                JsonObject jsonObject = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject
                String newsListJson = jsonObject.get("news_list").toString(); // 获取新闻列表的JSON字符串

                Type listType = new TypeToken<List<News>>(){}.getType(); // 定义List<News>的类型

                return gson.fromJson(newsListJson, listType);
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
    public boolean add_comment(long news_id, String user_email, String comment) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id", news_id);
            jsonObject.put("user_email", user_email);
            jsonObject.put("comment", comment);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/add_comment") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "add_comment failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "add_comment failed", e);
            return false;
        }
    }

    @Override
    public boolean delete_comment(long news_id, String user_email) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id", news_id);
            jsonObject.put("user_email", user_email);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(api + "/delete_comment") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "delete_comment failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "delete_comment failed", e);
            return false;
        }
    }

    @Override
    public boolean add_like(long news_id, String user_email) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id", news_id);
            jsonObject.put("user_email", user_email);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(api + "/add_like") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "add_like failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "add_like failed", e);
            return false;
        }
    }

    @Override
    public boolean delete_like(long news_id, String user_email) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id", news_id);
            jsonObject.put("user_email", user_email);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        Request request = new Request.Builder()
                .url("http://your-server-url/delete_like") // 替换为你的服务端地址
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "delete_like failed, response code: " + response.code());
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "delete_like failed", e);
            return false;
        }
    }

    @Override
    public List<News> get_like_list() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(api + "/get_news_list") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                /**
                 * 处理网络响应，解析JSON数据并将其转换为News对象的列表。
                 *
                 * @param response 包含从服务器接收的响应数据的对象。
                 * @param tag 用于日志记录的标签。
                 * @return 无返回值。
                 */
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                Log.d(tag, responseData); // 日志记录响应数据

                Gson gson = new Gson(); // 创建Gson实例
                JsonParser parser = new JsonParser(); // 创建JsonParser实例
                JsonObject jsonObject = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject
                String newsListJson = jsonObject.get("news_list").toString(); // 获取新闻列表的JSON字符串

                Type listType = new TypeToken<List<News>>(){}.getType(); // 定义List<News>的类型

                return gson.fromJson(newsListJson, listType);
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
    public List<FollowUser> get_follow_list() {

        JSONObject _jsonObject = new JSONObject();
        try {
            _jsonObject.put("user_email", "2116040314@s.upc.edu.cn");
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
        }

        RequestBody body = RequestBody.create(_jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(api + "/get_follow_list") // 替换为你的服务端地址
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                Log.d(tag, responseData); // 日志记录响应数据

                Gson gson = new Gson(); // 创建Gson实例
                JsonParser parser = new JsonParser(); // 创建JsonParser实例
                JsonObject jsonObject = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject
                String newsListJson = jsonObject.get("follow_list").toString(); // 获取新闻列表的JSON字符串
                Type listType = new TypeToken<List<FollowUser>>(){}.getType(); // 定义List<FollowUser>的类型

                return gson.fromJson(newsListJson, listType);
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

