package com.stop.loveam.dao;

import com.stop.loveam.entity.News;

import java.util.List;

public interface NewsDao {

    boolean add_news(News news);

    boolean delete_news(long id);

    boolean modify_news(News news);

    List<News> search_news(String keyword);

    News get_news(long id);

    List<News> get_news_list();

    boolean add_comment(long news_id, String user_email, String comment);

    boolean delete_comment(long ews_id, String user_email);

    boolean add_like(long news_id, String user_email);

    boolean delete_like(long news_id, String user_email);

}
