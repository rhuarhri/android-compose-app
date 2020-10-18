package com.example.testcomposeapp.data.internal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Database {

    private var currentScore = Score(0)
    private val scoreDisplay = MutableLiveData<Int>()

    init {
        scoreDisplay.value = currentScore.total
    }

    fun getScore() : LiveData<Int>
    {
        return scoreDisplay
    }

    fun setScore(newScore : Int)
    {
        currentScore.total = newScore
        scoreDisplay.value = currentScore.total
    }

}