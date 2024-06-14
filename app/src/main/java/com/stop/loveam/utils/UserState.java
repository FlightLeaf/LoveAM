package com.stop.loveam.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserState {
    public static void setLoginState(Context context, boolean isLogin)
    {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.apply();
    }
    public static boolean getLoginState(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }
}
