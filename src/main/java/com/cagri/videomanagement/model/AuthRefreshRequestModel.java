package com.cagri.videomanagement.model;

/**
 * Created by cagri.dursun on 16.8.2016.
 */
public class AuthRefreshRequestModel {

    private String refreshToken;

    private int videoManagerId;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getVideoManagerId() {
        return videoManagerId;
    }

    public void setVideoManagerId(int videoManagerId) {
        this.videoManagerId = videoManagerId;
    }
}
