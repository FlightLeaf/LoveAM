package com.stop.loveam.dao.Impl;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stop.loveam.dao.UserDao;
import com.stop.loveam.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserDaoImpl implements UserDao {

    private static final String TAG = "UserDao";

    public static String code;

    private final OkHttpClient client;
    private final MediaType mediaType;

    private JSONObject jsonObject;

    String tag = "UserDaoImpl";

    String api = "http://117.72.69.162:5008";

    public UserDaoImpl() {
        this.client = new OkHttpClient();
        this.mediaType = MediaType.parse("application/json; charset=utf-8");
    }

    @Override
    public boolean login(String email, String password) {
        // 创建登录请求的 JSON 数据
        jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api+"/login")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "Login failed: " + response);
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean register(User user) {
        jsonObject = new JSONObject();
        if(!Objects.equals(UserDaoImpl.code, code)){
            //Toast.makeText(null, "验证码错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            jsonObject.put("email", user.getEmail());
            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("image", user.getImage());
            jsonObject.put("label", user.getLabel());
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error"+e);
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api + "/register") // 替换为实际的注册接口 URL
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "Register failed: " + response);
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean send(String email) {
        code = String.valueOf((int)(Math.random()*1000000));
        jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("code", code);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error"+e);
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api + "/send") // 替换为实际的发送邮件接口 URL
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "Send failed: " + response);
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modify_password(String email, String new_password) {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("new_password", new_password);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api + "/modify_password") // 替换为实际的修改密码接口 URL
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "Modify password failed: " + response);
                return false;
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modify_user_info(User user) {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("email", user.getEmail());
            jsonObject.put("name", user.getName());
            jsonObject.put("image", user.getImage());
            jsonObject.put("label", user.getLabel());
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return false;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api + "/modify_user_info") // 替换为实际的修改用户信息接口 URL
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                return true;
            } else {
                Log.e(tag, "Modify user info failed: " + response);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User get_user_info(String email) {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
        } catch (JSONException e) {
            Log.e(tag, "JSONObject error");
            return null;
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(api + "/get_user_info")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string(); // 将响应体转换为字符串
                Log.d(tag, responseData); // 日志记录响应数据
                JsonParser parser = new JsonParser(); // 创建JsonParser实例
                JsonObject json = parser.parse(responseData).getAsJsonObject(); // 将响应数据解析为JsonObject
                JsonElement data = json.getAsJsonObject().get("data");
                System.out.println("Data: " + data);

                JsonElement user_email = data.getAsJsonArray().get(0);
                JsonElement name = data.getAsJsonArray().get(1);
                JsonElement imageUrl = data.getAsJsonArray().get(2);
                JsonElement description = data.getAsJsonArray().get(3);
                JsonElement date = data.getAsJsonArray().get(4);

                User user = new User();
                user.setEmail(user_email.getAsString());
                user.setName(name.getAsString());
                user.setImage(imageUrl.getAsString());
                user.setLabel(description.getAsString());
                user.setCreatedAt(date.getAsString());
                return user;
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return null;
        }
        return null;
    }
}

