package com.whispcorp.whispme.database.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = WhispNotification.TB_WHISPNOTIFICATION)
public class WhispNotification {
    public static final String TB_WHISPNOTIFICATION = "whisps_notifications";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int viewerUserId;
    private int senderUserId;
    private String message;
    private Date createdAt;
    private float latitude;
    private float longitude;
    private boolean state;

    public WhispNotification(int id, int viewerUserId, int senderUserId, String message, Date createdAt, float latitude, float longitude, boolean state) {
        this.id = id;
        this.viewerUserId = viewerUserId;
        this.senderUserId = senderUserId;
        this.message = message;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
    }

    public WhispNotification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewerUserId() {
        return viewerUserId;
    }

    public void setViewerUserId(int viewerUserId) {
        this.viewerUserId = viewerUserId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
