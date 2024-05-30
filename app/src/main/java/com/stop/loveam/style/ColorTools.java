package com.stop.loveam.style;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.view.ViewCompat;

public class ColorTools {
    /**
     * 针对Android应用，设置指定Activity的状态栏颜色。
     *
     * @param activity 指定要设置状态栏颜色的 Activity。
     * @param statusColor 想要设置的状态栏颜色的整型颜色值。
     */
    public  static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        // 取消状态栏透明效果
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置窗口可以绘制状态栏背景
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // 设置指定的颜色作为状态栏颜色
        window.setStatusBarColor(statusColor);
        // 确保系统状态栏是可见的
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        // 配置应用内容视图不适应系统窗口（如状态栏和导航栏）的大小
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }
}
