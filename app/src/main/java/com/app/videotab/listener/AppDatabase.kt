package com.app.videotab.listener

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.videotab.model.Feed
import com.app.videotab.model.FeedDao

@Database(entities = [Feed::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
        abstract fun feedDao(): FeedDao
}