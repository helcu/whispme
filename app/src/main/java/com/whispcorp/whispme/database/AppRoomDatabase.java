package com.whispcorp.whispme.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.database.entities.Whisp;

@Database(entities = {Whisp.class, User.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    public final static String DB_WHISPME_APP = "db_whispme_app";
}
