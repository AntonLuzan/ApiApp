package com.example.apiapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {

    // Insert a post, replacing it if already exists (based on the primary key)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Post post);

    // Get all posts as LiveData for observing changes
    @Query("SELECT * FROM Post")
    LiveData<List<Post>> getAllPosts();  // Returns LiveData for observing changes

    // Get all posts as a regular List (no LiveData observation)
    @Query("SELECT * FROM Post")
    List<Post> getAllFavorites();  // No LiveData here; simple list for non-observable operations
}
