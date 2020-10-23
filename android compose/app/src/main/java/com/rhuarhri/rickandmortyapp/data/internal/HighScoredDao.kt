package com.rhuarhri.rickandmortyapp.data.internal

import androidx.room.*

@Dao
interface HighScoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setHighScore(highScore: SavedHighScore)

    @Update
    fun updateScore(highScore: SavedHighScore)

    @Query("SELECT * FROM highScore")
    fun getScore() : List<SavedHighScore>

}