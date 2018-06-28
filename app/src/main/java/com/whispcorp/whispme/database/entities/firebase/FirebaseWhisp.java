package com.whispcorp.whispme.database.entities.firebase;

import java.io.Serializable;

public class FirebaseWhisp implements Serializable {
    private String serverId;
    private String ownerServerId;
    private String type;
    private String title;

    public FirebaseWhisp() {
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getOwnerServerId() {
        return ownerServerId;
    }

    public void setOwnerServerId(String ownerServerId) {
        this.ownerServerId = ownerServerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
