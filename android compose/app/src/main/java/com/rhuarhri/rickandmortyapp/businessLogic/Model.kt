package com.rhuarhri.rickandmortyapp.businessLogic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rhuarhri.rickandmortyapp.data.DatabaseHandler

class Model constructor(private val databaseHandler : DatabaseHandler) {

    private val score = MutableLiveData<Int>()
    fun getScore() : LiveData<Int>
    {
        return score
    }

    private val lives = MutableLiveData<Int>()
    private val startLivesAmount : Int = 5
    fun getLives() : LiveData<Int>
    {
        return lives
    }

    fun getHighScore() : LiveData<Int>
    {
        return databaseHandler.database.getHighScore()
    }

    init {
        replay()
    }

    private fun increaseScore()
    {
        val oldScore : Int = score.value!!

        score.value = (oldScore + 1)

        checkHighScore()
    }

    private fun removeLife()
    {
        val currentLives : Int = lives.value!!

        lives.value = (currentLives - 1)
    }

    private fun checkHighScore()
    {
        val currentHighScore : Int? = databaseHandler.database.getHighScore().value

        if (currentHighScore != null) {
            if (score.value!! > currentHighScore) {
                databaseHandler.database.setHighScore(score.value!!)
            }
        }
    }

    fun checkAnswer(userAnswer : String)
    {
        val answer = databaseHandler.apiHandler.getAnswer()

        if (answer.isBlank())
        {
            //user gets point by default
            increaseScore()
        }
        else
        {
            when (answer) {
                userAnswer -> {
                    //answer correct
                    increaseScore()
                }
                "unknown" -> {
                    //answer is unknown so cannot be answered
                    increaseScore()
                }
                else -> {
                    //answer wrong
                    removeLife()
                }
            }
        }

        databaseHandler.apiHandler.getRandomCharacter()
    }

    fun replay()
    {
        score.value = 0
        lives.value = startLivesAmount
    }

}