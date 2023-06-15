package com.kishan.cricketapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MatchDao {
    @Insert
    suspend fun saveMatchScore(match:MatchEntity)

    @Delete
    suspend fun deleteMatchScore(match: MatchEntity)

    @Query("SELECT * FROM cricketMatch ORDER BY matchId DESC")
     fun getAllMatchScore() : LiveData<MutableList<MatchEntity>>
}