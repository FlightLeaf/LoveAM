package com.stop.loveam.entity.videos;


import com.alibaba.fastjson2.annotation.JSONField;

public class Videos implements java.io.Serializable {

  @JSONField(name = "id")
  private long id;
  @JSONField(name = "name")
  private String name;
  @JSONField(name = "artistname")
  private String artistname;
  @JSONField(name = "desc")
  private String desc;
  @JSONField(name = "cover")
  private String cover;
  @JSONField(name = "publishtime")
  private String publishtime;
  @JSONField(name = "email")
  private String email;

  public Videos() {
  }

  public Videos(long id, String name, String artistname, String desc, String cover, String publishtime, String email) {
    this.id = id;
    this.name = name;
    this.artistname = artistname;
    this.desc = desc;
    this.cover = cover;
    this.publishtime = publishtime;
    this.email = email;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getArtistname() {
    return artistname;
  }

  public void setArtistname(String artistname) {
    this.artistname = artistname;
  }


  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }


  public String getPublishtime() {
    return publishtime;
  }

  public void setPublishtime(String publishtime) {
    this.publishtime = publishtime;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
