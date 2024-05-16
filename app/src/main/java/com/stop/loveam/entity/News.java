package com.stop.loveam.entity;

public class News {
    private String title;
    private String description;
    private String imageUrl;

    private String date;

    private String source;

    public News(String title, String description, String imageUrl, String date, String source) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.source = source;
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
}

