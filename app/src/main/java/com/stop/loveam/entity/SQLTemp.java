package com.stop.loveam.entity;

public class SQLTemp {

    String class_name;

    String class_money;

    String class_num;

    public SQLTemp(String class_name, String class_money, String class_num) {
        this.class_name = class_name;
        this.class_money = class_money;
        this.class_num = class_num;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_money() {
        return class_money;
    }

    public void setClass_money(String class_money) {
        this.class_money = class_money;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }
}
