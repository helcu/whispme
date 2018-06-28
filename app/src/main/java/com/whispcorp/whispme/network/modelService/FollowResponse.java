package com.whispcorp.whispme.network.modelService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowResponse {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("follower")
    @Expose
    private String follower;
    @SerializedName("followed")
    @Expose
    private OwnerResponse followed;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public FollowResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public OwnerResponse getFollowed() {
        return followed;
    }

    public void setFollowed(OwnerResponse followed) {
        this.followed = followed;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
