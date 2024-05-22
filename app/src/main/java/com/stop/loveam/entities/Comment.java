package com.stop.loveam.entities;


public class Comment {

  private long id;
  private String userEmail;
  private long newsId;
  private String comment;
  private java.sql.Timestamp createdAt;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }

}
