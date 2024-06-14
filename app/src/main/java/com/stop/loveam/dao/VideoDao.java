package com.stop.loveam.dao;

import com.stop.loveam.entity.videos.Videos;

import java.util.List;

public interface VideoDao {

    List<Videos> get_video_list();

    String get_video_url(String id);
}
