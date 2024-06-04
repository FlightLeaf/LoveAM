package com.stop.loveam.entity.constellation;

import java.io.Serializable;

public class Fortunetext implements Serializable {
    private String all;
    private String love;
    private String work;
    private String money;
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