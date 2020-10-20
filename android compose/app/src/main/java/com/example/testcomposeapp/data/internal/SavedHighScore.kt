package com.example.testcomposeapp.data.internal

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "highScore")
data class SavedHighScore (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "score") var highScore : Int?
)
{}