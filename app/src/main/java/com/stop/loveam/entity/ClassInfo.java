package com.stop.loveam.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ClassInfo{
// 使用SerializedName注解指定JSON中的键名与字段对应

    @SerializedName("class_id")
    private String class_id_select;

    @SerializedName("class_name")
    private String class_name_select;

    @SerializedName("class_type")
    private String class_type_select;

    @SerializedName("class_credit")
    private String class_credit_select;

    @SerializedName("class_hour")
    private String class_hour_select;

    @SerializedName("selected")
    private boolean isSelected;


    public ClassInfo(String class_id_select, String className, String classType, String credit, String hours) {
        this.class_id_select = class_id_select;
        this.class_name_select = className;
        this.class_type_select = classType;
        this.class_credit_select = credit;
        this.class_hour_select = hours;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getClass_id_select() {
        return class_id_select;
    }

    public void setClass_id_select(String class_id_select) {
        this.class_id_select = class_id_select;
    }

    public String getClass_name_select() {
        return class_name_select;
    }

    public void setClass_name_select(String class_name_select) {
        this.class_name_select = class_name_select;
    }

    public String getClass_type_select() {
        return class_type_select;
    }

    public void setClass_type_select(String class_type_select) {
        this.class_type_select = class_type_select;
    }

    public String getClass_credit_select() {
        return class_credit_select;
    }

    public void setClass_credit_select(String class_credit_select) {
        this.class_credit_select = class_credit_select;
    }

    public String getClass_hour_select() {
        return class_hour_select;
    }

    public void setClass_hour_select(String class_hour_select) {
        this.class_hour_select = class_hour_select;
    }

    // FromJSON方法
    public static ClassInfo fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, ClassInfo.class);
    }

    // ToJSON方法
    public String toJson() {
        return new Gson().toJson(this);
    }
}
