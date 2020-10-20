package com.example.testcomposeapp.data

import androidx.lifecycle.LiveData
import com.example.testcomposeapp.businessLogic.Model
import com.example.testcomposeapp.data.external.Character


class Repository constructor(private val databaseHandler: DatabaseHandler, private val model : Model,) {

    fun getScore() : LiveData<Int>
    {
        return model.getScore()
    }

    fun getLives() : LiveData<Int>
    {
        return model.getLives()
    }

    fun getHighScore() : LiveData<Int>
    {
        return model.getHighScore()
    }

    fun getCharacter() : LiveData<Character>
    {
        return databaseHandler.apiHandler.getCurrentCharacter()
    }

    fun checkAnswer(answer : String)
    {
        model.checkAnswer(answer)
    }


    //how to implement singleton
    companion object
    {
        @Volatile private var instance: Repository? = null

        //?: this bit means that if instance is null then it will return everything after the :
        //if not null it returns instance
        fun getInstance(database: DatabaseHandler, model : Model) = instance ?: synchronized(this){
            instance ?: Repository(database, model).also { instance = it }
        }

    }
}