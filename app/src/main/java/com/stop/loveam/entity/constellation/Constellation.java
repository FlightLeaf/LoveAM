package com.stop.loveam.entity.constellation;

import java.io.Serializable;

public class Constellation implements Serializable {
    private String title;
    private String time;
    private Todo todo;
    private Fortune fortune;
    private Fortunetext index;
    private String shortcomment;
    private Fortunetext fortunetext;
    private String type;
    private String uptype;
    private String luckynumber;
    private String luckycolor;
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
