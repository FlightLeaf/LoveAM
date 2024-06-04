package com.stop.loveam.entity;

import java.io.Serializable;

//{
//    "zh": "心有多远，你就能走多远。",
//    "en": "You can go as far as you want to go.",
//    "pic": "https://staticedu-wps.cache.iciba.com/image/60c4b0b16daa6141b4dc1b9c9dc1b652.jpg"
//}
public class EnglishShortSentences implements Serializable {
    private String zh;
    private String en;
    private String pic;

    public EnglishShortSentences() {
    }

    public EnglishShortSentences(String zh, String en, String pic) {
        this.zh = zh;
        this.en = en;
        this.pic = pic;
    }

    public String getZh() { return zh; }
    public void setZh(String value) { this.zh = value; }

    public String getEn() { return en; }
    public void setEn(String value) { this.en = value; }

    public String getPic() { return pic; }
    public void setPic(String value) { this.pic = value; }
}

