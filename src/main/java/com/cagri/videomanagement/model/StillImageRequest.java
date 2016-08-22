package com.cagri.videomanagement.model;

/**
 * Created by cagri.dursun on 18.8.2016.
 */
public class StillImageRequest {

    private double timeStamp;

    private String downloadedVideoUrl;

    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDownloadedVideoUrl() {
        return downloadedVideoUrl;
    }

    public void setDownloadedVideoUrl(String downloadedVideoUrl) {
        this.downloadedVideoUrl = downloadedVideoUrl;
    }
}
