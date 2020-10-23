package com.rhuarhri.rickandmortyapp.data.internal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Database constructor(context: Context) {

    private val currentHighScore = MutableLiveData<Int>()
    private val databaseName : String = "HighScoreDatabase"
    private val scoreDB : HighScoreDB

    init
    {
        scoreDB = Room.databaseBuilder(context, HighScoreDB::class.java, databaseName ).build()
        setup()
    }

    private fun setup()
    {
        CoroutineScope(IO).launch {
            val isDatabaseEmpty = scoreDB.highScoreDao().getScore().isEmpty()
            if (isDatabaseEmpty)
            {
                set(0)
            }
            else
            {
                val highScore = get()
                withContext(Main)
                {
                    currentHighScore.value = highScore.highScore
                }
            }
        }
    }

    fun getHighScore() : LiveData<Int>
    {
        /*
        CoroutineScope(IO).launch {
            val result : Int? = get().highScore
            withContext(Main)
            {
                currentHighScore.value = result
            }
        }*/

        return currentHighScore
    }


    private suspend fun get() : SavedHighScore
    {
        val highScoreResult = scoreDB.highScoreDao().getScore()
        return highScoreResult[0]
    }

    fun setHighScore(newScore : Int)
    {
        CoroutineScope(IO).launch {
            set(newScore)
        }
    }

    private suspend fun set(newScore : Int)
    {
        val isDatabaseEmpty = scoreDB.highScoreDao().getScore().isEmpty()
        if (isDatabaseEmpty){
            val defaultValue : SavedHighScore = SavedHighScore(uid = 0, highScore = newScore)
            scoreDB.highScoreDao().setHighScore(defaultValue)
        }
        else
        {
            val currentHighScore : SavedHighScore = get()
            currentHighScore.highScore = newScore
            scoreDB.highScoreDao().updateScore(currentHighScore)
        }

        withContext(Main)
        {
            currentHighScore.value = newScore
        }
    }

}