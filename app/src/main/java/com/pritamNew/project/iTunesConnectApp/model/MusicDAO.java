package com.pritamNew.project.iTunesConnectApp.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MusicDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Music music);

    @Update
    void update(Music books);

    @Delete
    void delete(Music books);

    @Query("SELECT * FROM tbl_music")
    LiveData<List<Music>> getAllMusic();

    @Query("SELECT * FROM tbl_music WHERE artistName==:artistName")
    LiveData<List<Music>> getMusic(String artistName);

}
