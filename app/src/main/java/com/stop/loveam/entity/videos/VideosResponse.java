package com.stop.loveam.entity.videos;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

public class VideosResponse {
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "video_list")
    private List<Videos> video_list;

    public VideosResponse() {
    }

    public VideosResponse(String message, String status, List<Videos> video_list) {
        this.message = message;
        this.status = status;
        this.video_list = video_list;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public List<Videos> getVideo_list() {
        return video_list;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVideo_list(List<Videos> video_list) {
        this.video_list = video_list;
    }
}

