package com.stop.loveam.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.stop.loveam.R;

public class GlideEngine implements ImageEngine {

    /**
     * 加载图片
     *
     * @param context   上下文
     * @param url       资源url
     * @param imageView 图片承载控件
     */
    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context   上下文
     * @param url       资源url
     * @param imageView 图片承载控件
     * @param maxWidth  最大宽度
     * @param maxHeight 最大高度
     */
    @Override
    public void loadImage(Context context, ImageView imageView, String url, int maxWidth, int maxHeight) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .override(maxWidth, maxHeight)
                .into(imageView);
    }

    /**
     * 加载相册目录封面
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    @Override
    public void loadAlbumCover(Context context, String url, ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context)
                .asBitmap()
                .load(url)
                .override(180, 180)
                .sizeMultiplier(0.5f)
                .transform(new CenterCrop(), new RoundedCorners(8))
                .placeholder(R.drawable.book)
                .into(imageView);
    }


    /**
     * 加载图片列表图片
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    @Override
    public void loadGridImage(Context context, String url, ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .override(200, 200)
                .centerCrop()
                .placeholder(R.drawable.book)
                .into(imageView);
    }



    /**
     * 暂停所有的Glide请求。
     *
     * @param context 上下文环境，用于访问应用的资源和其他组件。
     */
    @Override
    public void pauseRequests(Context context) {
        // 验证上下文的有效性，无效则直接返回
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        // 暂停所有的Glide请求
        Glide.with(context).pauseRequests();
    }

    /**
     * 恢复所有的Glide请求。
     *
     * @param context 上下文环境，用于访问应用的资源和其他组件。
     */
    @Override
    public void resumeRequests(Context context) {
        // 验证上下文的有效性，无效则直接返回
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        // 恢复所有的Glide请求
        Glide.with(context).resumeRequests();
    }

    // 私有构造函数，防止外部实例化
    private GlideEngine() {
    }

    /**
     * 单例模式，确保GlideEngine的唯一实例。
     */
    private static final class InstanceHolder {
        static final GlideEngine instance = new GlideEngine();
    }

    /**
     * 获取GlideEngine的单例实例。
     *
     * @return 返回GlideEngine的单例实例。
     */
    public static GlideEngine createGlideEngine() {
        return InstanceHolder.instance;
    }
}
