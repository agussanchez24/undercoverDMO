package com.example.undercover

class GameManager(
    private val playerNames: List<String>,
    private val wordPair: Pair<String, String>, // Ej: ("gato", "tigre")
    private val numUndercover: Int = 1,
    private val includeMrWhite: Boolean = true
) {
    val players: List<Player>

    init {
        // Barajamos los jugadores
        val shuffledNames = playerNames.shuffled()

        // Asignamos roles según la cantidad de jugadores
        val roles = mutableListOf<Role>()

        repeat(numUndercover) { roles.add(Role.UNDERCOVER) }
        if (includeMrWhite) roles.add(Role.MR_WHITE)
        while (roles.size < playerNames.size) roles.add(Role.CIUDADANO)
        roles.shuffle()

        // Creamos la lista de jugadores con sus roles y palabras
        players = shuffledNames.mapIndexed { index, name ->
            Player(
                name = name,
                role = roles[index]
            )
        }
    }

    // Devuelve la palabra asignada al jugador según su rol
    fun getWordForPlayer(player: Player): String {
        return when (player.role) {
            Role.CIUDADANO -> wordPair.first
            Role.UNDERCOVER -> wordPair.second
            Role.MR_WHITE -> "" // No tiene palabra
        }
    }
}
