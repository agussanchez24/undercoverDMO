package com.example.undercover


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.undercover.GameManager
import com.example.undercover.Player

@Composable
fun WordRevealScreen(
    players: List<Player>,
    gameManager: GameManager,
    onFinish: () -> Unit // Se llama cuando todos los jugadores vieron su palabra
) {
    // Estado para llevar el índice actual
    var currentIndex by remember { mutableStateOf(0) }
    var revealed by remember { mutableStateOf(false) }

    val currentPlayer = players[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Turno de:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(currentPlayer.name, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        if (!revealed) {
            Button(onClick = { revealed = true }) {
                Text("Mostrar palabra")
            }
        } else {
            val word = gameManager.getWordForPlayer(currentPlayer)
            Text(
                text = if (word.isNotEmpty()) "Tu palabra es:\n$word" else "No tenés palabra, improvisá!",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    revealed = false
                    if (currentIndex < players.size - 1) {
                        currentIndex++
                    } else {
                        onFinish()
                    }
                }
            ) {
                Text("Siguiente jugador")
            }
        }
    }
}
