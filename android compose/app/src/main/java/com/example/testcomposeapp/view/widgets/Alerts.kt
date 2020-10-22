package com.example.testcomposeapp.view.widgets

import android.content.Context
import androidx.compose.foundation.Text
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.testcomposeapp.data.DatabaseHandler
import com.example.testcomposeapp.view.AppViewModel

class Alerts {

    val showGameOverAlert = mutableStateOf(false)
    private val gameOverTile : String = "Game Over"
    private val gameOverMessage : String = "Would you like to play again?"
    fun showGameOverAlert()
    {
        showGameOverAlert.value = true
    }
    fun hideGameOverAlert()
    {
        showGameOverAlert.value = false
    }
    @Composable
    fun gameOverAlert(replayGameFun : () -> Unit) {
        if (showGameOverAlert.value) {
            showAlert(title = gameOverTile, message = gameOverMessage, onClick = replayGameFun)

        }
    }

    val showInfoAlert = mutableStateOf(false)
    private val APIWebsite = "https://rickandmortyapi.com/"
    private val infoTitle : String = "Important information"
    private val infoMessage : String = "All information is provided by " +
            "Rick and Morty API available from $APIWebsite."

    fun showInfoAlert()
    {
        showInfoAlert.value = true
    }

    @Composable
    fun userInfoAlert() {
        if (showInfoAlert.value) {
            showAlert(title = infoTitle, message = infoMessage, onClick = {})

        }
    }

    @Composable
    private fun showAlert(title : String, message : String, onClick : () -> Unit)
    {
        AlertDialog(
                onDismissRequest = {
                    showGameOverAlert.value = false
                    showInfoAlert.value = false
                },
                title = {
                    Text(title)
                },
                text = {
                    Text(message)
                },
                confirmButton = {
                    Button( onClick = onClick)
                    {
                        Text("OK")
                    }
                }
        )
    }

    companion object
    {
        @Volatile private var instance: Alerts? = null

        //?: this bit means that if instance is null then it will return everything after the :
        //if not null it returns instance
        fun getInstance() = instance ?: synchronized(this){
            instance ?: Alerts().also { instance = it }
        }

    }
}