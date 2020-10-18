package com.example.testcomposeapp.data.external

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHandler {

    private var characterAmount : Int = 0
    private val character = MutableLiveData<Character>()

    private val baseUrl : String = "https://rickandmortyapi.com/"

    private lateinit var retrofit: Retrofit

    init
    {
        retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        getRandomCharacter()

    }

    fun getRandomCharacter()
    {
        CoroutineScope(Dispatchers.IO).launch{

            if (characterAmount == 0) {
                getCharacterAmount()

            }

            val location : Int = (0..(characterAmount + 1)).random()

            getCharacterInfo(location)

        }
    }

    private suspend fun getCharacterAmount()
    {
        val apiInterface = retrofit.create(ApiInterface::class.java)

        val action = apiInterface.getCount()

        val response = action.execute()

        if (response.isSuccessful)
        {
            if (response.body() != null)
            {
                withContext(Main)
                {
                    characterAmount = response.body()!!.info.count
                }
            }
            else
            {
                Log.e("character amount", "character amount is null")
            }
        }
        else
        {
            Log.e("Retrofit", "Retro fit failed to find anything")
        }
    }

    private suspend fun getCharacterInfo(characterLocation : Int)
    {
        val apiInterface = retrofit.create(ApiInterface::class.java)

        val action = apiInterface.getCharacter(characterLocation)

        val response = action.execute()

        if (response.isSuccessful) {
            if (response.body() != null) {
                withContext(Main)
                {
                    character.value = response.body()!!
                }
            }
            else
            {
                Log.e("character", "character is null")
            }
        }
        else
        {
            Log.e("Retrofit", "Retro fit failed to find any characters")
        }
    }

    fun getCurrentCharacter() : LiveData<Character>
    {
        return character
    }

    fun getAnswer() : String
    {
        return if (character.value != null) character.value!!.status else ""
    }

}