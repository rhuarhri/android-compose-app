package com.example.testcomposeapp.data

import com.example.testcomposeapp.data.external.ApiHandler
import com.example.testcomposeapp.data.internal.Database
import javax.inject.Inject


class DatabaseHandler {

    var database = Database()
    var apiHandler = ApiHandler()

    companion object
    {
        @Volatile private var instance: DatabaseHandler? = null

        //?: this bit means that if instance is null then it will return everything after the :
        //if not null it returns instance
        fun getInstance() = instance ?: synchronized(this){
            instance ?: DatabaseHandler().also { instance = it }
        }

    }
}