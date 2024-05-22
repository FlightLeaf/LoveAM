package com.stop.loveam.dao;

import com.stop.loveam.entity.News;

public interface NewsDao {
    public boolean addNews(News news);
    public boolean delNews(String id);
    public News getNews(String id);
    public News getRandomNews();
    public boolean addLikes(String id);
}
