package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.model.*
import com.example.madlevel4task2.dao.ResultDao
import com.example.madlevel4task2.database.HistoryRoomDatabase

class ResultRepository(context: Context) {
    private val resultDao: ResultDao

    init {
        val historyRoomDatabase = HistoryRoomDatabase.getDatabase(context)
        resultDao = historyRoomDatabase!!.resultDao()
    }

    suspend fun getAllResults(): List<Result> {
        return resultDao.getAllResults()
    }

    suspend fun insertResult(result: Result) {
        resultDao.insertResult(result)
    }

    suspend fun deleteResult(result: Result) {
        resultDao.deleteResult(result)
    }

    suspend fun deleteAllResults() {
        resultDao.deleteAllResults()
    }
}