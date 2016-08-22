package com.cagri.videomanagement.model;

/**
 * Created by cagri.dursun on 17.8.2016.
 */
public class StillModel {

    private String quality;

    private String url;

    private DimensionModel dimension;

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(DimensionModel dimension) {
        this.dimension = dimension;
    }
}
