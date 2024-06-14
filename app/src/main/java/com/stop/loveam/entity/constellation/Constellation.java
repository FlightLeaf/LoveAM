package com.stop.loveam.entity.constellation;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;

public class Constellation implements Serializable {
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "time")
    private String time;
    @JSONField(name = "todo")
    private Todo todo;
    @JSONField(name = "fortune")
    private Fortune fortune;
    @JSONField(name = "index")
    private Fortunetext index;
    @JSONField(name = "shortcomment")
    private String shortcomment;
    @JSONField(name = "fortunetext")
    private Fortunetext fortunetext;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "uptype")
    private String uptype;
    @JSONField(name = "luckynumber")
    private String luckynumber;
    @JSONField(name = "luckycolor")
    private String luckycolor;
    @JSONField(name = "luckyconstellation")
    private String luckyconstellation;

    public Constellation() {
    }

    public Constellation(String title, String time,
                         Todo todo, Fortune fortune,
                         Fortunetext index, String shortcomment,
                         Fortunetext fortunetext, String type,
                         String uptype, String luckynumber,
                         String luckycolor, String luckyconstellation) {
        this.title = title;
        this.time = time;
        this.todo = todo;
        this.fortune = fortune;
        this.index = index;
        this.shortcomment = shortcomment;
        this.fortunetext = fortunetext;
        this.type = type;
        this.uptype = uptype;
        this.luckynumber = luckynumber;
        this.luckycolor = luckycolor;
        this.luckyconstellation = luckyconstellation;
    }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public Todo getTodo() { return todo; }
    public void setTodo(Todo value) { this.todo = value; }

    public Fortune getFortune() { return fortune; }
    public void setFortune(Fortune value) { this.fortune = value; }

    public Fortunetext getIndex() { return index; }
    public void setIndex(Fortunetext value) { this.index = value; }

    public String getShortcomment() { return shortcomment; }
    public void setShortcomment(String value) { this.shortcomment = value; }

    public Fortunetext getFortunetext() { return fortunetext; }
    public void setFortunetext(Fortunetext value) { this.fortunetext = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getUptype() { return uptype; }
    public void setUptype(String value) { this.uptype = value; }

    public String getLuckynumber() { return luckynumber; }
    public void setLuckynumber(String value) { this.luckynumber = value; }

    public String getLuckycolor() { return luckycolor; }
    public void setLuckycolor(String value) { this.luckycolor = value; }

    public String getLuckyconstellation() { return luckyconstellation; }
    public void setLuckyconstellation(String value) { this.luckyconstellation = value; }
}
