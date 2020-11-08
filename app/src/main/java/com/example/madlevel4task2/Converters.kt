package com.example.madlevel4task2

import androidx.room.TypeConverter
import com.example.madlevel4task2.model.*
import java.util.*

class Converters {
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time?.toLong()
    }
}