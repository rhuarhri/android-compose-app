package com.example.testcomposeapp.view.widgets

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageAsset
import com.example.testcomposeapp.view.AppViewModel


class WidgetBuilder(private val viewModel: AppViewModel, private val context: Context)
{
    //app states
    val characterName = mutableStateOf("")

    val characterGender = mutableStateOf("")

    val characterSpecies = mutableStateOf("")

    val image = mutableStateOf<ImageAsset?>(null)

    val score = mutableStateOf(0)

    val lives = mutableStateOf(0)

    val highScore = mutableStateOf(0)

    val questionDisplay = mutableStateOf("")

    val disabled = mutableStateOf(false)

    val isLandscape = mutableStateOf<Boolean>(false)

    val screenSize : ScreenChecker

    var isScreenSmall : Boolean = true

    init {
        screenSize = ScreenChecker(context)
        isScreenSmall = screenSize.isScreenSmall()
    }

    val playerStatus = PlayerStatusWidget(score = score, lives = lives,
            highScore = highScore, isScreenSmall = isScreenSmall)
    val question = QuestionDisplayWidget(questionDisplay, isScreenSmall)
    val characterDisplay = CharacterDisplayWidget(
            name = characterName,
            image = image,
            gender = characterGender,
            species = characterSpecies,
            isLandscape = isLandscape,
            isScreenSmall = isScreenSmall
    )
    val userAnswer = UserAnswerWidget(viewModel, isScreenSmall, disabled)

    class ScreenChecker(context : Context)
    {
        var width : Int = 0
        var height : Int = 0

        init {
            val measurements = context.resources.displayMetrics
            height = measurements.heightPixels
            width = measurements.widthPixels
        }

        fun isScreenSmall() : Boolean
        {
            return height <= 600
        }
    }
}