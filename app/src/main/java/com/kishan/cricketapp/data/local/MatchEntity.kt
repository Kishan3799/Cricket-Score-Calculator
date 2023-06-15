package com.kishan.cricketapp.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cricketMatch")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val matchId: Long  ,

    @ColumnInfo("team_name")
    val matchTeamName:String,

    @ColumnInfo("match_scores")
    val matchScore:Int,

    @ColumnInfo("match_wickets")
    val matchWicket:Int,

    @ColumnInfo("match_balls")
    val matchBall:Int,

    @ColumnInfo("match_overs")
    val matchOvers:Int
)
