package com.rhuarhri.rickandmortyapp.data.external

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/character/{id}")
    fun getCharacter(@Path("id") location : Int) : Call<Character>

    @GET("api/character")
    fun getCount() : Call<CharacterCount>
}