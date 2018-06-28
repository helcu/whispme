package com.whispcorp.whispme.database.entities.firebase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirebaseComment implements Serializable {
    private String serverId;
    private String ownerId;
    private String whispId;
    private String content;
    private String createdAt;

    public FirebaseComment() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = simpleDateFormat.format(new Date());
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getWhispId() {
        return whispId;
    }

    public void setWhispId(String whispId) {
        this.whispId = whispId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
