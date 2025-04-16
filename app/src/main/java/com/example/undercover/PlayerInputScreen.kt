package com.example.undercover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PlayerInputScreen(
    onStartGame: (List<String>) -> Unit
) {
    var playerNames by remember { mutableStateOf(listOf("")) }

    fun updateName(index: Int, newName: String) {
        playerNames = playerNames.toMutableList().also {
            it[index] = newName
        }
    }

    fun addPlayer() {
        playerNames = playerNames + ""
    }

    fun removePlayer(index: Int) {
        if (playerNames.size > 1) {
            playerNames = playerNames.toMutableList().also {
                it.removeAt(index)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Ingrese los nombres de los jugadores", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        playerNames.forEachIndexed { index, name ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { updateName(index, it) },
                    placeholder = { Text("Jugador ${index + 1}") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { removePlayer(index) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar jugador")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { addPlayer() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar jugador")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val filtered = playerNames.map { it.trim() }.filter { it.isNotEmpty() }
                if (filtered.size >= 3) {
                    onStartGame(filtered)
                }
            },
            enabled = playerNames.count { it.trim().isNotEmpty() } >= 3,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comenzar juego")
        }
    }
}

