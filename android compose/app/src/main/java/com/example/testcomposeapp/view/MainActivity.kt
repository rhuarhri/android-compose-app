package com.example.testcomposeapp.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.AlertDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testcomposeapp.utils.InjectorUtils
import com.example.testcomposeapp.view.widgets.Alerts
import com.example.testcomposeapp.view.widgets.WidgetBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel

    private lateinit var widgetBuilder : WidgetBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideCountViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        widgetBuilder = WidgetBuilder(viewModel, applicationContext)

        setupObserver()

        widgetBuilder.highScore.value = viewModel.initialHighScore

        setContent {
                mainScreen()
        }

    }

    private fun setupObserver() {
        viewModel.getCharacterData().observe(this, Observer {
            widgetBuilder.characterName.value = it.name
            widgetBuilder.characterSpecies.value = it.species
            widgetBuilder.characterGender.value = it.gender

            viewModel.getImage(it.imageURL)

        })

        viewModel.getImageAsset().observe(this, Observer<ImageAsset> {
            widgetBuilder.image.value = it

            //data loaded
            checkGameOver()
        })

        viewModel.getScore().observe(this, Observer {
            widgetBuilder.score.value = it
        })

        viewModel.getLives().observe(this, Observer {lives ->
            widgetBuilder.lives.value = lives

        })

        viewModel.getHighScore().observe(this, Observer {
            widgetBuilder.highScore.value = it
        })

    }



    @Composable
    fun mainScreen() {

        MaterialTheme() {

            Scaffold(
                    topBar = {
                        TopAppBar(title = { title() },
                                actions = {
                                    IconButton(onClick = {
                                        Alerts.getInstance().showInfoAlert()
                                    })
                                    {
                                        Icon(Icons.Filled.Info)
                                        Alerts.getInstance().userInfoAlert()
                                    }
                                })
                    },
                    bottomBar = { widgetBuilder.userAnswer.widget() }
            ) {

                Column(Modifier.fillMaxHeight()) {

                    widgetBuilder.playerStatus.widget()
                    widgetBuilder.question.widget()
                    widgetBuilder.characterDisplay.widget()
                    //alert
                    Alerts.getInstance().gameOverAlert(replayGameFun = {restGame()})
                }

            }
        }
    }

    @Composable
    fun title() {
        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val titleMod: Modifier = Modifier.constrainAs(widget)
            {
                //top.linkTo(parent.top, margin = 16.dp)
                //start.linkTo(parent.start, margin = 16.dp)
                //end.linkTo(parent.end, margin = 16.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
                width = Dimension.fillToConstraints //i.e. match parent
                //centerHorizontallyTo(parent)

            }

            MaterialTheme() {
                val textStyle = if (widgetBuilder.isScreenSmall) MaterialTheme.typography.h4 else MaterialTheme.typography.h3
                Text("Rick and Morty quiz",
                        style = textStyle,
                        modifier = titleMod)

            }
        }
    }

    //private val question : String =
    //private val gameOver : String = "GAME OVER"
    private fun checkGameOver()
    {
        if (widgetBuilder.lives.value > 0) {

            widgetBuilder.disabled.value = false
        }
        else
        {
            Alerts.getInstance().showGameOverAlert()
        }
    }

    private fun restGame()
    {
        viewModel.replayGame()
        widgetBuilder.disabled.value = false
        Alerts.getInstance().hideGameOverAlert()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        widgetBuilder.isLandscape.value = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
        super.onConfigurationChanged(newConfig)
    }



}
