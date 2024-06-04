package com.stop.loveam.entity.weather;

import java.io.Serializable;

public class Night implements Serializable {
    private String type;
    private String fengxiang;
    private String fengli;

    public Night() {
    }

    public Night(String type, String fengxiang, String fengli) {
        this.type = type;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
    }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getFengxiang() { return fengxiang; }
    public void setFengxiang(String value) { this.fengxiang = value; }

    public String getFengli() { return fengli; }
    public void setFengli(String value) { this.fengli = value; }
}
