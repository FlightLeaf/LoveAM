package com.stop.loveam.entity.constellation;

import java.io.Serializable;

public class Fortune implements Serializable {
    private long all;
    private long love;
    private long work;
    private long money;
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