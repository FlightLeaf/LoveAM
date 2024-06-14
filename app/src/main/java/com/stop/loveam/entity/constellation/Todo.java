package com.stop.loveam.entity.constellation;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;

public class Todo implements Serializable {
    @JSONField(name = "yi")
    private String yi;
    @JSONField(name = "ji")
    private String ji;

    public Todo() {
    }

    public Todo(String yi, String ji) {
        this.yi = yi;
        this.ji = ji;
    }

    public String getYi() { return yi; }
    public void setYi(String value) { this.yi = value; }

    public String getJi() { return ji; }
    public void setJi(String value) { this.ji = value; }
}