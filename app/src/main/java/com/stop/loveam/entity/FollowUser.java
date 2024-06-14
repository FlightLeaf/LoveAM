package com.stop.loveam.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FollowUser implements Serializable {
    @SerializedName("follow_user_email")
    private String followUserEmail;
    private String image;
    private String name;

    public FollowUser() {
    }

    public FollowUser(String followUserEmail, String image, String name) {
        this.followUserEmail = followUserEmail;
        this.image = image;
        this.name = name;
    }

    public String getFollowUserEmail() { return followUserEmail; }
    public void setFollowUserEmail(String value) { this.followUserEmail = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}