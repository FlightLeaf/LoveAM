package com.stop.loveam.entity;


import java.io.Serializable;

public class User implements Serializable {

  private String email;
  private String name;
  private String password;
  private String image;
  private String label;
  private String createdAt;

  public User() {
  }

  public User(String email, String name, String password, String image, String label, String createdAt) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.image = image;
    this.label = label;
    this.createdAt = createdAt;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

}
