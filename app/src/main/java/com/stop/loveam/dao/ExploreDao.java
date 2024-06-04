package com.stop.loveam.dao;

import com.stop.loveam.entity.RESTCalendar;

public interface ExploreDao {

    /**
     * ● 摸鱼日历：<a href="https://api.vvhan.com/api/moyu?type=json">...</a>
     * */
    public RESTCalendar getRESTCalendar();

    /**
     * ● 随机风景图片：<a href="https://api.vvhan.com/api/wallpaper/views?type=json">...</a>
     * */
    public String getRandomPicture();

    /**
     * ● 头像：<a href="https://api.vvhan.com/api/avatar/rand?type=json">...</a>
     * */
    public String getAvatar();

    /**
     * ● 男生头像：<a href="https://api.vvhan.com/api/avatar/boy?type=json">...</a>
     * */
    public String getBoyAvatar();

    /**
     * ● 女生头像：<a href="https://api.vvhan.com/api/avatar/girl?type=json">...</a>
     * */
    public String getGirlAvatar();

    /**
     * ● 60秒读懂世界https://api.vvhan.com/api/60s
     * */
    public String get60s();

    /**
     * ● 英语短句：<a href="https://api.vvhan.com/api/dailyEnglish?type=sj">...</a>
     * */
    public String getEnglishSentence();

    /**
     * ● 星座数据：<a href="https://api.vvhan.com/article/horoscope.html">...</a>
     * */
    public String getHoroscope();

    /**
     * ● 天气：<a href="https://api.vvhan.com/api/weather?city=北京">...</a>
     * */
    public String getWeather(String city);


    /**
     *  ● 随即一言：<a href="https://zj.v.api.aa1.cn/api/wenan-wm/?type=json">...</a>
     * */
    public String getRandomSaying();
}
