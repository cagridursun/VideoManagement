package com.cagri.videomanagement.model;

import java.util.List;

/**
 * Created by cagri.dursun on 16.8.2016.
 */
public class AuthenticationResponseModel {

    private String accessToken;

    private String refreshToken;

    private List<VideoManagerModel> videoManagerList;

    private int validForVideoManager;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<VideoManagerModel> getVideoManagerList() {
        return videoManagerList;
    }

    public void setVideoManagerList(List<VideoManagerModel> videoManagerList) {
        this.videoManagerList = videoManagerList;
    }

    public int getValidForVideoManager() {
        return validForVideoManager;
    }

    public void setValidForVideoManager(int validForVideoManager) {
        this.validForVideoManager = validForVideoManager;
    }
}
