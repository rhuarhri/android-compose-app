package com.example.testcomposeapp.data.external

import com.google.gson.annotations.SerializedName

class Character {

    @SerializedName("name")
    var name : String = ""

    @SerializedName("species")
    var species : String = ""

    @SerializedName("gender")
    var gender : String = ""

    @SerializedName("status")
    var status : String = ""

    @SerializedName("image")
    var imageURL : String = ""

    override fun toString(): String {
        return "Name: $name Gender: $gender Species: $species"
    }
}