package com.whispcorp.whispme.database.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Comment.TB_COMMENT,
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "owner",
                        onDelete = CASCADE),
                @ForeignKey(entity = Whisp.class,
                        parentColumns = "id",
                        childColumns = "whispId",
                        onDelete = CASCADE)})

public class Comment {

    public static final String TB_COMMENT = "Comment";


    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String serverId;
    private Integer owner;
    private Integer whispId;
    private String content;


    public Comment() {
    }

    public Comment(String serverId, Integer owner, Integer whispId, String content) {
        this.serverId = serverId;
        this.owner = owner;
        this.whispId = whispId;
        this.content = content;
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

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getWhispId() {
        return whispId;
    }

    public void setWhispId(Integer whispId) {
        this.whispId = whispId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
