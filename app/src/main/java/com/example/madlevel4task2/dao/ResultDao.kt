package com.example.madlevel4task2.dao

import com.example.madlevel4task2.model.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {

    @Query("SElECT * FROM resultTable")
    suspend fun getAllResults(): List<Result>

    @Insert
    suspend fun insertResult(result: Result)

    @Delete
    suspend fun deleteResult(result: Result)

    @Query("DELETE FROM resultTable")
    suspend fun deleteAllResults()
}