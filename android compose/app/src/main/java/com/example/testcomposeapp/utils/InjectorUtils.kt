package com.example.testcomposeapp.utils

import com.example.testcomposeapp.businessLogic.Model
import com.example.testcomposeapp.data.DatabaseHandler
import com.example.testcomposeapp.data.Repository
import com.example.testcomposeapp.view.ViewModelFactory

object InjectorUtils {


    fun provideCountViewModelFactory() : ViewModelFactory
    {
        val repo = Repository.getInstance(DatabaseHandler.getInstance(), Model(
            DatabaseHandler.getInstance())
        )
        return ViewModelFactory(repo)
    }
}