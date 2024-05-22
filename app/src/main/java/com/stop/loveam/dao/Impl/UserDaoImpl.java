package com.stop.loveam.dao.Impl;

import android.util.Log;

import com.stop.loveam.dao.UserDao;
import com.stop.loveam.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    String tag = "UserDaoImpl";

    String api = "http://your-server/";

    public UserDaoImpl() {
        this.client = new OkHttpClient();
        this.mediaType = MediaType.parse("application/json; charset=utf-8");
    }

    @Override
    public boolean login(String email, String password) {
        // 创建登录请求的 JSON 数据
        JSONObject jsonObject = new JSONObject();
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
        JSONObject jsonObject = new JSONObject();
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
        JSONObject jsonObject = new JSONObject();
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
        JSONObject jsonObject = new JSONObject();
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
        JSONObject jsonObject = new JSONObject();
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
        JSONObject jsonObject = new JSONObject();
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
                String responseData = response.body().string();
                // TODO: 解析 JSON 数据，并返回 User 对象
                Log.d(tag, "Response data: " + responseData);
            }
        } catch (IOException e) {
            Log.e(tag, "IOException: " + e.getMessage());
            return null;
        }
        return null;
    }
}

