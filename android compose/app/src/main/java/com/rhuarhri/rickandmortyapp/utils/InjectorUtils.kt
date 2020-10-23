package com.rhuarhri.rickandmortyapp.utils

import android.content.Context
import com.rhuarhri.rickandmortyapp.businessLogic.Model
import com.rhuarhri.rickandmortyapp.data.DatabaseHandler
import com.rhuarhri.rickandmortyapp.data.Repository
import com.rhuarhri.rickandmortyapp.view.ViewModelFactory

object InjectorUtils {


    fun provideCountViewModelFactory(context : Context) : ViewModelFactory
    {
        val repo = Repository.getInstance(DatabaseHandler.getInstance(context), Model(
            DatabaseHandler.getInstance(context))
        )
        return ViewModelFactory(repo)
    }
}