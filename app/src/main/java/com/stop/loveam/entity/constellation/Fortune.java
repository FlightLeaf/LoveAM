package com.stop.loveam.entity.constellation;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;

public class Fortune implements Serializable {
    @JSONField(name = "all")
    private long all;
    @JSONField(name = "love")
    private long love;
    @JSONField(name = "work")
    private long work;
    @JSONField(name = "money")
    private long money;
    @JSONField(name = "health")
    private long health;

    public long getAll() { return all; }
    public void setAll(long value) { this.all = value; }

    public long getLove() { return love; }
    public void setLove(long value) { this.love = value; }

    public long getWork() { return work; }
    public void setWork(long value) { this.work = value; }

    public long getMoney() { return money; }
    public void setMoney(long value) { this.money = value; }

    public long getHealth() { return health; }
    public void setHealth(long value) { this.health = value; }
}