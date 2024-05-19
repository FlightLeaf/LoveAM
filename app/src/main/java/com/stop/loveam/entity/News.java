package com.stop.loveam.entity;

import com.google.gson.Gson;

public class News {

    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String date;
    private String source;
    private String likes;
    private String url;

    public News(){

    }

    public News(String id, String title, String description, String imageUrl, String date, String source, String likes, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.source = source;
        this.likes = likes;
        this.url = url;
    }


    public static News fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, News.class);
    }

    // ToJSON方法
    public String toJson() {
        return new Gson().toJson(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

