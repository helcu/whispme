package com.whispcorp.whispme.network.modelService.trendService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordenateResponse {

    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
