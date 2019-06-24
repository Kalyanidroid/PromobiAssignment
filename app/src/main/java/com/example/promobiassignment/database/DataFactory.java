package com.example.promobiassignment.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.promobiassignment.database.dao.FeedsDao;
import com.example.promobiassignment.database.entities.Feed;


@Database(entities = {Feed.class}, version = 1, exportSchema = false)
public abstract class DataFactory extends RoomDatabase {

    private static DataFactory INSTANCE;
    public abstract FeedsDao feedsDao();
}
