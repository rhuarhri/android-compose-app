package com.example.testcomposeapp.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testcomposeapp.utils.InjectorUtils

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel

    private var characterName = mutableStateOf("")

    private var characterGender = mutableStateOf("")

    private var characterSpecies = mutableStateOf("")

    private var image = mutableStateOf<ImageAsset?>(null)

    private var score = mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideCountViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        setupObserver()

        setContent {
                mainScreen()
        }

    }

    private fun setupObserver() {
        viewModel.getCharacterData().observe(this, Observer {
            characterName.value = it.name
            characterSpecies.value = it.species
            characterGender.value = it.gender

            viewModel.getImage(it.imageURL)
        })

        viewModel.getImageAsset().observe(this, Observer<ImageAsset> {
            image.value = it
        })

        viewModel.getScore().observe(this, Observer {
            score.value = it
        })

    }

    @Composable
    fun mainScreen() {

        MaterialTheme() {

            Scaffold(
                    topBar = {
                        TopAppBar(title = { title() })
                    },
                    bottomBar = { answer() }
            ) {

                Column(Modifier.fillMaxHeight()) {

                    score()
                    question()
                    characterDisplay()
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

                Text("Rick and morty quiz",
                        style = MaterialTheme.typography.h3,
                        modifier = titleMod)

            }
        }
    }

    @Composable
    fun score() {

        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val scoreMod: Modifier = Modifier.constrainAs(widget)
            {
                //bottom.linkTo(parent.bottom, margin = 16.dp)
                top.linkTo(parent.top, margin = 16.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                //absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
                //width = Dimension.fillToConstraints //i.e. match parent
            }

            MaterialTheme() {
                Text("Your score is ${score.value}",
                        style = MaterialTheme.typography.h6,
                        modifier = scoreMod)
            }
        }
    }

    @Composable
    fun question() {
        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val questionMod = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = 16.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }

            MaterialTheme() {
                Text("Is this character dead or alive?",
                        style = MaterialTheme.typography.h5,
                        modifier = questionMod)
            }
        }
    }

    @Composable
    fun characterDisplay() {

        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val characterMod: Modifier = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = 16.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }

            Row(characterMod)
            {
                if (image.value != null) {
                    Image(image.value!!, Modifier.preferredSizeIn(minHeight = 60.dp,
                            minWidth = 60.dp, maxHeight = 600.dp, maxWidth = 600.dp))
                }
                Column(Modifier.padding(16.dp)) {

                    Text("name")
                    Text(text = characterName.value)

                    Text("species")
                    Text(text = characterSpecies.value)

                    Text("gender")
                    Text(text = characterGender.value)
                }
            }
        }

    }

    @Composable
    fun answer() {
        ConstraintLayout(Modifier.fillMaxWidth())
        {
            val (widget) = createRefs()

            val answerMod: Modifier = Modifier.constrainAs(widget)
            {
                top.linkTo(parent.top, margin = 16.dp)
                absoluteLeft.linkTo(parent.absoluteLeft, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }

            Row(answerMod) {
                Button(onClick = {viewModel.checkAnswer("Dead")}, Modifier.padding(16.dp)) {
                    Text("Dead")
                }

                Button(onClick = {viewModel.checkAnswer("Alive")}, Modifier.padding(16.dp))
                {
                    Text("Alive")
                }
            }
        }
    }

}
