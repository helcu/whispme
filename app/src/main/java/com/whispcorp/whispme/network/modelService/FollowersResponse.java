package com.whispcorp.whispme.network.modelService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowersResponse {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("follower")
    @Expose
    private OwnerResponse follower;
    @SerializedName("followed")
    @Expose
    private String followed;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public FollowersResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OwnerResponse getFollower() {
        return follower;
    }

    public void setFollower(OwnerResponse follower) {
        this.follower = follower;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
