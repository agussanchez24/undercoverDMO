package com.example.undercover

// Representa a cada jugador del juego
data class Player(
    val name: String,         // Nombre del jugador
    val role: Role,           // Rol asignado (Ciudadano, Undercover, Mr.White)
    var isEliminated: Boolean = false // Indica si el jugador fue eliminado
)

// Posibles roles de los jugadores
enum class Role {
    CIUDADANO,     // Tiene la palabra correcta
    UNDERCOVER,  // Tiene la palabra parecida
    MR_WHITE     // No tiene palabra, debe improvisar
}

