package com.stop.loveam.view;

import android.content.res.Resources;
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

public class NewsAdapterSmall extends RecyclerView.Adapter<NewsAdapterSmall.NewsViewHolder> {

    //生成示例数据
    private List<News> newsList;

    public NewsAdapterSmall(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_b, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDescription.setText(news.getDescription());
        holder.newsDate.setText(news.getDate());
        holder.newsSource.setText(news.getSource());
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels; // 获取屏幕宽度

        Glide.with(holder.itemView.getContext())
                .load(news.getImageUrl())
                .override(screenWidth-30, screenWidth-30) // 设置宽度为屏幕宽度，高度为原始高度
                .into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
        TextView newsDescription;
        TextView newsDate;
        TextView newsSource;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsSource = itemView.findViewById(R.id.newsSource);
        }
    }
}

