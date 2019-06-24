package com.example.promobiassignment.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.promobiassignment.database.entities.Feed;

import java.util.List;

@Dao
public interface FeedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Feed> feeds);

    @Query("SELECT * FROM feeds")
    List<Feed> getAll();

    @Query("SELECT * FROM feeds WHERE feeds.displayTitle LIKE :query OR feeds.headline LIKE :query OR feeds.summaryShort LIKE :query")
    List<Feed> searchFeeds(String query);
}
