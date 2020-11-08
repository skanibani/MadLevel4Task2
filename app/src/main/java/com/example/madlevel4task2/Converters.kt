package com.example.madlevel4task2

import androidx.room.TypeConverter
import com.example.madlevel4task2.model.*
import java.util.*

class Converters {

    // This uses the long as a converter type
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time.toLong()
    }

    @TypeConverter
    fun toDate(date: Long): Date {
        return Date(date)
    }

    // This uses the string as a converter type
    @TypeConverter
    fun fromMove(move: Move): String {
        return move.name
    }

    @TypeConverter
    fun toMove(move: String): Move {
        return Move.valueOf(move)
    }

    @TypeConverter
    fun fromWinner(winner: Winner): String {
        return winner.name
    }

    @TypeConverter
    fun toWinner(winner: String): Winner {
        return Winner.valueOf(winner)
    }
}