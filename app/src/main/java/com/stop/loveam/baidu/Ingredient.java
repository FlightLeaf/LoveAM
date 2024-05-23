package com.stop.loveam.baidu;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class Ingredient {
    public static String ingredient(String filePath) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            String accessToken = "24.a38e852276b7835bef015c362847cdd6.2592000.1719026166.282335-73827817";

            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("result")) {
                String result_str = jsonObject.getString("result");
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Model>>(){}.getType();
                List<Model> person = gson.fromJson(result_str, listType);
                return person.get(0).getName();
            }
            return result;
        } catch (Exception e) {
            Log.d("ingredient", e.getMessage());
        }
        return null;
    }
}
