package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.*
import com.example.madlevel4task2.Converters
import com.example.madlevel4task2.model.*
import com.example.madlevel4task2.dao.ResultDao

@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HistoryRoomDatabase: RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {

        private const val DATABASE_NAME = "HISTORY_DATABASE"

        @Volatile
        private var historyRoomDatabaseInstance: HistoryRoomDatabase? = null

        fun getDatabase(context: Context): HistoryRoomDatabase? {
            if (historyRoomDatabaseInstance == null) {
                synchronized(HistoryRoomDatabase::class.java) {
                    if (historyRoomDatabaseInstance == null) {
                        historyRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            HistoryRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return historyRoomDatabaseInstance
        }
    }
}