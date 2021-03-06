package com.rhuarhri.rickandmortyapp.view.widgets

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class QuestionDisplayWidget(private val isScreenSmall: Boolean)
{

    private val topPadding : Int = if (isScreenSmall) 5 else 16
    private val question : String = "Is this character dead or alive?"

    @Composable
    fun widget() {
        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val questionMod = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = topPadding.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }

            MaterialTheme() {
                val textStyle = if (isScreenSmall) MaterialTheme.typography.h6 else MaterialTheme.typography.h5
                Text(question,
                        style = textStyle,
                        modifier = questionMod)
            }
        }
    }
}