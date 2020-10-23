package com.rhuarhri.rickandmortyapp.data.internal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SavedHighScore::class), version = 1)
abstract class HighScoreDB : RoomDatabase() {
    abstract fun highScoreDao() : HighScoredDao
}