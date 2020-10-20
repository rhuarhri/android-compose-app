package com.example.testcomposeapp.data

import android.content.Context
import com.example.testcomposeapp.data.external.ApiHandler
import com.example.testcomposeapp.data.internal.Database

class DatabaseHandler constructor(context : Context) {

    var database = Database(context)
    var apiHandler = ApiHandler()

    companion object
    {
        @Volatile private var instance: DatabaseHandler? = null

        //?: this bit means that if instance is null then it will return everything after the :
        //if not null it returns instance
        fun getInstance(context: Context) = instance ?: synchronized(this){
            instance ?: DatabaseHandler(context).also { instance = it }
        }

    }
}