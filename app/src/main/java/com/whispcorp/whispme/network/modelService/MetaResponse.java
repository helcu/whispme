package com.whispcorp.whispme.network.modelService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaResponse {

    @SerializedName("likes")
    @Expose
    private Integer likes;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("comments")
    @Expose
    private Integer comments;


    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
