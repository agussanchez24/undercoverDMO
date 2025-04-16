package com.example.undercover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.undercover.ui.theme.UndercoverTheme
import com.example.undercover.PlayerInputScreen
import com.example.undercover.WordRevealScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember { mutableStateOf("input") }
            var gameManager: GameManager? by remember { mutableStateOf(null) }

            when (screen) {
                "input" -> PlayerInputScreen { names ->
                    gameManager = GameManager(
                        playerNames = names,
                        wordPair = "gato" to "tigre", // Palabras fijas por ahora
                        numUndercover = 1,
                        includeMrWhite = true
                    )
                    screen = "reveal"
                }

                "reveal" -> WordRevealScreen(
                    players = gameManager!!.players,
                    gameManager = gameManager!!,
                    onFinish = {
                        // Acá iría la siguiente pantalla del juego
                        println("Todos vieron sus palabras. Empezamos el juego.")
                    }
                )
            }
        }
    }
}