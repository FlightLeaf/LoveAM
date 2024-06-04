package com.stop.loveam.entity.weather;


import java.io.Serializable;

public class Weather implements Serializable {
    private String city;
    private Data data;
    private Air air;
    private String tip;

    public Weather() {
    }

    public Weather(String city, Data data, Air air, String tip) {
        this.city = city;
        this.data = data;
        this.air = air;
        this.tip = tip;
    }

    public String getCity() { return city; }
    public void setCity(String value) { this.city = value; }

    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }

    public Air getAir() { return air; }
    public void setAir(Air value) { this.air = value; }

    public String getTip() { return tip; }
    public void setTip(String value) { this.tip = value; }
}
