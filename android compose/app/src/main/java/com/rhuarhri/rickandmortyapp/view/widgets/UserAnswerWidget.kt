package com.rhuarhri.rickandmortyapp.view.widgets

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rhuarhri.rickandmortyapp.view.AppViewModel

class UserAnswerWidget constructor(private val viewModel: AppViewModel,
                                   private val isScreenSmall : Boolean,
                                   private val disabled : MutableState<Boolean>)
{

    private val topPadding : Int = if (isScreenSmall) 5 else 16

    @Composable
    fun widget() {
        ConstraintLayout(Modifier.fillMaxWidth().height(150.dp))
        {
            val (widget) = createRefs()

            val answerMod: Modifier = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = topPadding.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
                //bottom.linkTo(parent.bottom, margin = topPadding.dp)
            }

            if (isScreenSmall)
            {
                smallVersion(constraints = answerMod)
            }
            else{
                normalVersion(constraints = answerMod)
            }


        }
    }

    @Composable
    private fun smallVersion(constraints : Modifier)
    {

        Row(constraints) {
            Button(onClick = {
                if (!disabled.value) {
                    viewModel.checkAnswer("Dead")
                    disabled.value = true
                }

            }, Modifier.padding(16.dp),
                    backgroundColor = if(disabled.value) Color.Gray else Color.Black) {
                Text("Dead", color = Color.White, style= MaterialTheme.typography.h6)
            }

            Button(onClick = {
                if (!disabled.value) {
                    viewModel.checkAnswer("Alive")
                    disabled.value = true
                }
            }, Modifier.padding(16.dp),
                    backgroundColor = if(disabled.value) Color.Gray else Color.Red)
            {
                Text("Alive", color = Color.White, style= MaterialTheme.typography.h6)
            }
        }
    }

    @Composable
    private fun normalVersion(constraints : Modifier)
    {
        Row(constraints) {
            Button(onClick = {
                if (!disabled.value) {
                    viewModel.checkAnswer("Dead")
                    disabled.value = true
                }

            }, Modifier.padding(16.dp),
                    backgroundColor = if(disabled.value) Color.Gray else Color.Black) {
                Text("Dead", color = Color.White, style= MaterialTheme.typography.h4)
            }

            Button(onClick = {
                if (!disabled.value) {
                    viewModel.checkAnswer("Alive")
                    disabled.value = true
                }
            }, Modifier.padding(16.dp),
                    backgroundColor = if(disabled.value) Color.Gray else Color.Red)
            {
                Text("Alive", color = Color.White, style= MaterialTheme.typography.h4)
            }
        }
    }
}