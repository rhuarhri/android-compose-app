package com.example.testcomposeapp.view.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.unit.dp

class CharacterDisplayWidget(private val name : MutableState<String>,
                             private val image : MutableState<ImageAsset?>,
                             private val gender : MutableState<String>,
                             private val species : MutableState<String>,
                             private val isLandscape : MutableState<Boolean>,
                             private val isScreenSmall : Boolean)
{
    private val topPadding : Int = if (isScreenSmall) 5 else 16

    @Composable
    fun widget() {

        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val characterMod: Modifier = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = topPadding.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }

            if (isScreenSmall)
            {
                smallLayout(constraints = characterMod)
            }
            else {
                if (isLandscape.value) {
                    landscapeLayout(constraints = characterMod)
                } else {
                    portraitLayout(constraints = characterMod)
                }
            }

        }

    }

    @Composable
    private fun portraitLayout(constraints: Modifier)
    {
        Column(constraints) {
            image()
            characterDetail(title = "name", info = name.value)
            characterDetail(title = "species", info = species.value)
            characterDetail(title = "gender", info = gender.value)
        }
    }

    @Composable
    private fun landscapeLayout(constraints : Modifier)
    {
        Row(constraints)
        {
            image()
            Column(Modifier.padding(topPadding.dp)) {

                characterDetail(title = "name", info = name.value)
                characterDetail(title = "species", info = species.value)
                characterDetail(title = "gender", info = gender.value)
            }
        }
    }

    @Composable
    private fun smallLayout(constraints: Modifier)
    {
        Column(constraints) {
            Box(alignment = Alignment.Center, modifier = Modifier.fillMaxWidth())
            {
                smallImage()
            }
            Box(alignment = Alignment.Center, modifier = Modifier.fillMaxWidth())
            {
                Row() {
                    characterDetail(title = "name", info = name.value)
                    characterDetail(title = "species", info = species.value)
                    characterDetail(title = "gender", info = gender.value)
                }
            }
        }
    }

    @Composable
    private fun image()
    {
        if (image.value != null) {
            Image(image.value!!, Modifier.preferredSizeIn(minHeight = 60.dp,
                    minWidth = 60.dp, maxHeight = 600.dp, maxWidth = 600.dp)
                    .clip(CircleShape))
        }
    }

    @Composable
    private fun smallImage()
    {

        if (image.value != null) {
            Image(image.value!!, modifier = Modifier.preferredSizeIn(minHeight = 60.dp,
                    minWidth = 60.dp, maxHeight = 150.dp, maxWidth = 150.dp)
                    .clip(CircleShape))
        }

    }

    @Composable
    private fun characterDetail(title: String, info: String)
    {
        val textSize = if (isScreenSmall) MaterialTheme.typography.body2 else MaterialTheme.typography.h6

        Column(Modifier.padding(16.dp)) {
            Text(title, style = textSize)
            Text(info)
        }
    }
}