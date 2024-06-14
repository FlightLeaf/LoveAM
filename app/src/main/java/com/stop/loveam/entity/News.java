package com.stop.loveam.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News  implements Serializable {

  private long id;
  private String title;
  private String description;
  private String imageurl;
  @SerializedName("created_at")
  private String createdAt;
  private String email;
  private long likes;
  private String url;

  private String name;

  public News(){

  }

  public News(long id, String title, String description, String imageurl, String createdAt, String email, long likes, String url, String name) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageurl = imageurl;
    this.createdAt = createdAt;
    this.email = email;
    this.likes = likes;
    this.url = url;
    this.name = name;
  }

  public News(long id, String title, String description, String imageurl, String createdAt,String url) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageurl = imageurl;
    this.createdAt = createdAt;
    this.url = url;
  }

  public News(String title, String description, String imageurl, String createdAt,String url) {
    this.title = title;
    this.description = description;
    this.imageurl = imageurl;
    this.createdAt = createdAt;
    this.url = url;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getLikes() {
    return likes;
  }

  public void setLikes(long likes) {
    this.likes = likes;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
