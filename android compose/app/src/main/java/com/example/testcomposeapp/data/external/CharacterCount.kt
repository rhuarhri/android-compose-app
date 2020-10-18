package com.example.testcomposeapp.data.external

import com.google.gson.annotations.SerializedName

class CharacterCount {

    @SerializedName("info")
    var info = Info()

}

class Info
{
    @SerializedName("count")
    var count : Int = 0
}