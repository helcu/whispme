package com.whispcorp.whispme.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = User.TB_USER)
public class User implements Serializable {

    public static final String TB_USER = "User";


    // password is manage with SharePreference
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String serverId;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String photo;
    private Integer followers;
    private Integer following;


    public User() {
    }

    public User(Integer id, String serverId, String username, String email, String password, String bio, String photo, Integer followers, Integer following) {
        this.id = id;
        this.serverId = serverId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.photo = photo;
        this.followers = followers;
        this.following = following;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }
}
