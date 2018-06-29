package com.whispcorp.whispme;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.androidnetworking.AndroidNetworking;
import com.whispcorp.whispme.database.AppRoomDatabase;

public class WhispmeApplication extends Application {

    private final String SP_WHISPME_APP = "sp_whispme_app";

    private static WhispmeApplication INSTANCE;

    private AppRoomDatabase database;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppRoomDatabase.class,
                AppRoomDatabase.DB_WHISPME_APP)
                .build();

        AndroidNetworking.initialize(getApplicationContext());

        sharedPreferences = getSharedPreferences(SP_WHISPME_APP, Context.MODE_PRIVATE);
    }

    public static WhispmeApplication getInstance() {
        return INSTANCE;
    }

    public AppRoomDatabase getDatabase() {
        return database;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
