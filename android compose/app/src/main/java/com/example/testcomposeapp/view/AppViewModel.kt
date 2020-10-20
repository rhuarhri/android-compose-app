package com.example.testcomposeapp.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.testcomposeapp.data.Repository
import com.example.testcomposeapp.data.external.Character
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class AppViewModel constructor(private val repository: Repository) : ViewModel() {

    var initialHighScore : Int = 0
    init{
        initialHighScore = if (getHighScore().value != null) getHighScore().value!! else 0

    }

    fun getScore() : LiveData<Int>
    {
        return repository.getScore()
    }

    fun getLives() : LiveData<Int>
    {
        return repository.getLives()
    }

    fun getHighScore() : LiveData<Int>
    {
        return repository.getHighScore()
    }

    fun getCharacterData() : LiveData<Character>
    {
        return repository.getCharacter()
    }

    fun checkAnswer(answer : String)
    {
        repository.checkAnswer(answer)
    }

    private val imageAsset = MutableLiveData<ImageAsset>()
    fun getImageAsset() : LiveData<ImageAsset>
    {
        return imageAsset
    }

    fun getImage(imageLoc : String){
        CoroutineScope(Dispatchers.IO).launch{
            val url : URL = URL(imageLoc)
            val foundImage : Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            withContext(Dispatchers.Main){
                imageAsset.value = foundImage.asImageAsset()
            }
        }
    }

}