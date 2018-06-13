package com.whispcorp.whispme.network.modelService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerResponse {

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("followers")
    @Expose
    private Integer followers;

    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;

    @SerializedName("update")
    @Expose
    private String update;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
