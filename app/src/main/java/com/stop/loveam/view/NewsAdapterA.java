package com.stop.loveam.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.stop.loveam.R;
import com.stop.loveam.entity.News;

import java.util.List;

/**
 * 用于展示新闻列表的适配器
 */
public class NewsAdapterA extends RecyclerView.Adapter<NewsAdapterA.NewsViewHolder> {

    // 存储新闻数据列表
    private final List<News> newsList;

    /**
     * 构造函数
     * @param newsList 新闻数据列表
     */
    public NewsAdapterA(List<News> newsList) {
        this.newsList = newsList;
    }

    /**
     * 创建并返回新闻项的ViewHolder
     * @param parent ViewHolder的父视图组
     * @param viewType 视图类型（在本例中未使用）
     * @return 新闻项的ViewHolder
     */
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载新闻项的布局文件，并返回一个初始化的ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_a, parent, false);
        return new NewsViewHolder(view);
    }

    /**
     * 绑定数据到正确的ViewHolder上
     * @param holder 需要绑定数据的ViewHolder
     * @param position 列表中的位置，对应一个新闻项
     */
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // 获取当前位置的新闻对象，并将数据设置到ViewHolder的视图组件上
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDescription.setText(news.getDescription());
        holder.newsDate.setText(news.getDate());
        holder.newsSource.setText(news.getSource());
        // 使用Glide库加载新闻图片
        Glide.with(holder.itemView.getContext()).load(news.getImageUrl()).into(holder.newsImage);
    }

    /**
     * 获取新闻列表的总项数
     * @return 新闻列表的项数
     */
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    /**
     * 新闻项的ViewHolder类
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage; // 新闻图片
        TextView newsTitle; // 新闻标题
        TextView newsDescription; // 新闻描述
        TextView newsDate; // 新闻日期
        TextView newsSource; // 新闻来源

        /**
         * 构造函数
         * @param itemView 单个新闻项的视图
         */
        public NewsViewHolder(View itemView) {
            super(itemView);
            // 通过id找到新闻项布局中的视图组件
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsSource = itemView.findViewById(R.id.newsSource);
        }
    }
}
