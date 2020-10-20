package com.example.testcomposeapp.view.widgets

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class PlayerStatusWidget(private val score : MutableState<Int>,
                         private val lives : MutableState<Int>,
                         private val highScore : MutableState<Int>,
                         private val isScreenSmall: Boolean)
{

    private val topPadding : Int = if(isScreenSmall) 5 else 16

    @Composable
    fun widget() {

        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val scoreMod: Modifier = Modifier.constrainAs(widget)
            {
                //bottom.linkTo(parent.bottom, margin = 16.dp)
                top.linkTo(parent.top, margin = topPadding.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
                //width = Dimension.fillToConstraints //i.e. match parent
            }

            Row(scoreMod) {
                score()
                lives()
                highScore()
            }
        }
    }

    @Composable
    private fun score(){
        val textStyle = if (isScreenSmall) MaterialTheme.typography.body1 else MaterialTheme.typography.h6
        val defaultMod = Modifier.padding(topPadding.dp)

        Text("Score: ${score.value}",
                style = textStyle,
                modifier = defaultMod)
    }

    @Composable
    private fun lives() {
        val textStyle = if (isScreenSmall) MaterialTheme.typography.body1 else MaterialTheme.typography.h6
        val defaultMod = Modifier.padding(topPadding.dp)

        Text("Lives: ${lives.value}",
                style = textStyle,
                modifier = defaultMod)
    }

    @Composable
    private fun highScore()
    {
        val textStyle = if (isScreenSmall) MaterialTheme.typography.body1 else MaterialTheme.typography.h6
        val defaultMod = Modifier.padding(topPadding.dp)

        Text("High score: ${highScore.value}",
                style = textStyle,
                modifier = defaultMod)
    }
}