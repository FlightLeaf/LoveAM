package com.stop.loveam.entity;

//{
//  "title": "[摸鱼人日历]06月01日",
//  "time": "6月1日",
//  "url": "https://web-static.4ce.cn/storage/bucket/v1/462f4608f44c4feba37b7406f772e8f8.png"
//}

import java.io.Serializable;

public class RESTCalendar implements Serializable {
    private String title;
    private String time;
    private String url;

    public RESTCalendar() {
    }

    public RESTCalendar(String title, String time, String url) {
        this.title = title;
        this.time = time;
        this.url = url;
    }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getUrl() { return url; }
    public void setUrl(String value) { this.url = value; }
}

