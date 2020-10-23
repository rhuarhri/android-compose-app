package com.rhuarhri.rickandmortyapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhuarhri.rickandmortyapp.data.Repository


class ViewModelFactory constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return AppViewModel(repository) as T
    }
}