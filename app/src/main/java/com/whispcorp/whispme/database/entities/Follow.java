package com.whispcorp.whispme.database.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Follow.TB_FOLLOW,
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "followed",
                        onDelete = CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "followed",
                        onDelete = CASCADE)})

public class Follow {

    public static final String TB_FOLLOW = "Follow";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer follower;
    private Integer followed;


    public Follow() {
    }

    public Follow(Integer follower, Integer followed) {
        this.follower = follower;
        this.followed = followed;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }
}
