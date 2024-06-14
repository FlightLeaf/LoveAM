package com.stop.loveam.entity.weather;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;

public class Air implements Serializable {
    @JSONField(name = "aqi")
    private long aqi;
    @JSONField(name = "aqi_level")
    private long aqiLevel;
    @JSONField(name = "aqi_name")
    private String aqiName;
    @JSONField(name = "co")
    private String co;
    @JSONField(name = "no2")
    private String no2;
    @JSONField(name = "o3")
    private String o3;
    @JSONField(name = "pm10")
    private String pm10;
    @JSONField(name = "pm2.5")
    private String pm25;
    @JSONField(name = "so2")
    private String so2;

    public Air() {
    }

    public Air(long aqi, long aqiLevel, String aqiName, String co, String no2, String o3, String pm10, String pm25, String so2) {
        this.aqi = aqi;
        this.aqiLevel = aqiLevel;
        this.aqiName = aqiName;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.so2 = so2;
    }

    public long getAqi() { return aqi; }
    public void setAqi(long value) { this.aqi = value; }

    public long getAqiLevel() { return aqiLevel; }
    public void setAqiLevel(long value) { this.aqiLevel = value; }

    public String getAqiName() { return aqiName; }
    public void setAqiName(String value) { this.aqiName = value; }

    public String getCo() { return co; }
    public void setCo(String value) { this.co = value; }

    public String getNo2() { return no2; }
    public void setNo2(String value) { this.no2 = value; }

    public String getO3() { return o3; }
    public void setO3(String value) { this.o3 = value; }

    public String getPm10() { return pm10; }
    public void setPm10(String value) { this.pm10 = value; }

    public String getPm25() { return pm25; }
    public void setPm25(String value) { this.pm25 = value; }

    public String getSo2() { return so2; }
    public void setSo2(String value) { this.so2 = value; }
}
