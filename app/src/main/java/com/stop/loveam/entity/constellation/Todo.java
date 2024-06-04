package com.stop.loveam.entity.constellation;

import java.io.Serializable;

public class Todo implements Serializable {
    private String yi;
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