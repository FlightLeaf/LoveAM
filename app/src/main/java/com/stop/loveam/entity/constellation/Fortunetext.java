package com.stop.loveam.entity.constellation;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;

public class Fortunetext implements Serializable {
    @JSONField(name = "all")
    private String all;
    @JSONField(name = "love")
    private String love;
    @JSONField(name = "work")
    private String work;
    @JSONField(name = "money")
    private String money;
    @JSONField(name = "health")
    private String health;

    public Fortunetext() {
    }

    public Fortunetext(String all, String love, String work, String money, String health) {
        this.all = all;
        this.love = love;
        this.work = work;
        this.money = money;
        this.health = health;
    }

    public String getAll() { return all; }
    public void setAll(String value) { this.all = value; }

    public String getLove() { return love; }
    public void setLove(String value) { this.love = value; }

    public String getWork() { return work; }
    public void setWork(String value) { this.work = value; }

    public String getMoney() { return money; }
    public void setMoney(String value) { this.money = value; }

    public String getHealth() { return health; }
    public void setHealth(String value) { this.health = value; }
}