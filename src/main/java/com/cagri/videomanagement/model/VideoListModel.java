package com.cagri.videomanagement.model;

import java.util.List;

/**
 * Created by cagri.dursun on 17.8.2016.
 */
public class VideoListModel {

    private int total;

    private List<VideoModel> videos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> videos) {
        this.videos = videos;
    }
}
