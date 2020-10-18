package com.example.testcomposeapp.businessLogic

import com.example.testcomposeapp.data.DatabaseHandler

class Model constructor(private val databaseHandler : DatabaseHandler) {

    private fun increaseScore()
    {
        val oldScore : Int = databaseHandler.database.getScore().value!!

        databaseHandler.database.setScore((oldScore + 1))
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
            if (answer == userAnswer)
            {
                //answer correct
                increaseScore()
            }
            else if (answer == "unknown")
            {
                increaseScore()
            }
        }

        databaseHandler.apiHandler.getRandomCharacter()
    }

}