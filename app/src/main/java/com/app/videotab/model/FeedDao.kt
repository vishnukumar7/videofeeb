package com.app.videotab.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Insert
    fun insert(feed: Feed)

    @Update
    fun update(feed: Feed)

    @Query("SELECT * FROM feed WHERE room_name=:name")
    fun getName(name: String) : List<Feed>

    @Query("SELECT * FROM FEED order by live DESC")
     fun getAll(): Flow<MutableList<Feed>>

     @Query("SELECT * FROM feed where live=:value")
     fun getCheck(value: Boolean): Flow<MutableList<Feed>>




}