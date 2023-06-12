package com.kishan.cricketapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao {
    @Insert
    suspend fun saveMatchScore(match:MatchEntity)

    @Query("SELECT * FROM cricketMatch")
     fun getAllMatchScore() : LiveData<List<MatchEntity>>
}