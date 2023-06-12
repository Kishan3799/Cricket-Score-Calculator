package com.kishan.cricketapp.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MatchEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var database : AppDataBase? = null
        private val DATABASE_NAME = "Cricket Score Calc"

        @Synchronized
        fun getInstance(context:Context): AppDataBase{
            if (database == null){
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }
    abstract fun matchDao() : MatchDao
}