package com.pritamNew.project.iTunesConnectApp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Music.class}, version = 1)
public abstract class DBHandler extends RoomDatabase {

    public abstract MusicDAO musicDAO();

    private static DBHandler instance;

    public static synchronized DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DBHandler.class, "db_music")
                    .fallbackToDestructiveMigration()
//                    .addCallback(callback)
                    .build();
        }
        return instance;
    }


}
