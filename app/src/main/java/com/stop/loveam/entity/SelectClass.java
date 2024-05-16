package com.stop.loveam.entity;

import androidx.annotation.NonNull;

public class SelectClass {
    String class_name;
    String class_credit;
    String class_hour;

    public SelectClass(String class_name, String class_credit, String class_hour) {
        this.class_name = class_name;
        this.class_credit = class_credit;
        this.class_hour = class_hour;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getClass_credit() {
        return class_credit;
    }

    public String getClass_hour() {
        return class_hour;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setClass_credit(String class_credit) {
        this.class_credit = class_credit;
    }

    public void setClass_hour(String class_hour) {
        this.class_hour = class_hour;
    }


    @NonNull
    public String toString() {
        return "SelectClass{" +
                "class_name='" + class_name + '\'' +
                ", class_credit='" + class_credit + '\'' +
                ", class_hour='" + class_hour + '\'' +
                '}';
    }
}
