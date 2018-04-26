package com.whispcorp.whispme.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = User.TB_USER)
public class User {

    public static final String TB_USER = "User";


    // password is manage with SharePreference
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String serverId;
    private String username;
    private String email;
    private String bio;
    private String photo;
    private Integer followers;


    public User() {
    }

    public User(Integer id, String serverId, String username, String email, String bio, String photo, Integer followers) {
        this.id = id;
        this.serverId = serverId;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.photo = photo;
        this.followers = followers;
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
}
