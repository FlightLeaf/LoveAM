package com.stop.loveam.entity.mv;

import com.alibaba.fastjson2.annotation.JSONField;

import java.time.LocalDate;

public class Mv implements java.io.Serializable{
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "artistName")
    private String artistName;
    @JSONField(name = "briefDesc")
    private String briefDesc;
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "cover")
    private String cover;
    @JSONField(name = "publishTime")
    private LocalDate publishTime;
    @JSONField(name = "brs")
    private BRS brs;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String value) { this.artistName = value; }

    public String getBriefDesc() { return briefDesc; }
    public void setBriefDesc(String value) { this.briefDesc = value; }

    public String getDesc() { return desc; }
    public void setDesc(String value) { this.desc = value; }

    public String getCover() { return cover; }
    public void setCover(String value) { this.cover = value; }

    public LocalDate getPublishTime() { return publishTime; }
    public void setPublishTime(LocalDate value) { this.publishTime = value; }

    public BRS getBrs() { return brs; }
    public void setBrs(BRS value) { this.brs = value; }
}
