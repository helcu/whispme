package com.whispcorp.whispme.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.whispcorp.whispme.database.entities.Whisp;

import java.util.List;

@Dao
public interface WhispDao {

    @Insert
    public void insert(Whisp whisp);

    @Query("SELECT *" +
            " FROM " + Whisp.TB_WHISP)
    public List<Whisp> getWhisps();

}
