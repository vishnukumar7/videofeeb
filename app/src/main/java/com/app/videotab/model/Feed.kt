package com.app.videotab.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Feed(

    @ColumnInfo(name = "room_name")
    @PrimaryKey
    val roomName: String,

    @ColumnInfo
    var live: Boolean,

    @ColumnInfo(name = "date_time")
    var dateTime: Long

)
