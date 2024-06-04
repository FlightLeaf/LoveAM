package com.stop.loveam.entity.weather;


import java.io.Serializable;

public class Data implements Serializable {
    private String date;
    private String week;
    private String type;
    private String low;
    private String high;
    private String fengxiang;
    private String fengli;
    private Night night;

    public Data() {
    }

    public Data(String date, String week, String type, String low, String high, String fengxiang, String fengli, Night night) {
        this.date = date;
        this.week = week;
        this.type = type;
        this.low = low;
        this.high = high;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.night = night;
    }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public String getWeek() { return week; }
    public void setWeek(String value) { this.week = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getLow() { return low; }
    public void setLow(String value) { this.low = value; }

    public String getHigh() { return high; }
    public void setHigh(String value) { this.high = value; }

    public String getFengxiang() { return fengxiang; }
    public void setFengxiang(String value) { this.fengxiang = value; }

    public String getFengli() { return fengli; }
    public void setFengli(String value) { this.fengli = value; }

    public Night getNight() { return night; }
    public void setNight(Night value) { this.night = value; }
}