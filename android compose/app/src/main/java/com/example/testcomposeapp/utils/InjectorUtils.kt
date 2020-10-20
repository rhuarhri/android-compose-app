package com.example.testcomposeapp.utils

import android.content.Context
import com.example.testcomposeapp.businessLogic.Model
import com.example.testcomposeapp.data.DatabaseHandler
import com.example.testcomposeapp.data.Repository
import com.example.testcomposeapp.view.ViewModelFactory

object InjectorUtils {


    fun provideCountViewModelFactory(context : Context) : ViewModelFactory
    {
        val repo = Repository.getInstance(DatabaseHandler.getInstance(context), Model(
            DatabaseHandler.getInstance(context))
        )
        return ViewModelFactory(repo)
    }
}