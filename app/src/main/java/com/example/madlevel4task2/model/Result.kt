package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "resultTable")
data class Result(
    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "playerMove")
    var playerMove: String,

    @ColumnInfo(name = "computerMove")
    var computerMove: String,

    @ColumnInfo(name = "winner")
    var winner: String,

    // Empty id column auto-increment as primary key
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)