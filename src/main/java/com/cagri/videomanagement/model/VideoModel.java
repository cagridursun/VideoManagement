package com.cagri.videomanagement.model;

import java.util.List;

/**
 * Created by cagri.dursun on 17.8.2016.
 */
public class VideoModel {

    private String id;

    private String title;

    private String description;

    private String thumbnail;

    private String length;

    private String createDate;

    private String modifiedDate;

    private String uploadDate;

    private int generation;

    private int plays;

    private int views;

    private boolean allFormatsAvailable;

    private CustomerMetaDataModel customerMetaData;

    private List<StillModel> stills;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isAllFormatsAvailable() {
        return allFormatsAvailable;
    }

    public void setAllFormatsAvailable(boolean allFormatsAvailable) {
        this.allFormatsAvailable = allFormatsAvailable;
    }

    public CustomerMetaDataModel getCustomerMetaData() {
        return customerMetaData;
    }

    public void setCustomerMetaData(CustomerMetaDataModel customerMetaData) {
        this.customerMetaData = customerMetaData;
    }

    public List<StillModel> getStills() {
        return stills;
    }

    public void setStills(List<StillModel> stills) {
        this.stills = stills;
    }
}
