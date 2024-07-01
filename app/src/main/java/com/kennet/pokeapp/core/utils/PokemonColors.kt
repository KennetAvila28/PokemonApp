package com.kennet.pokeapp.core.utils

object PokemonColors {
    private val typeColors = mapOf(
        "fighting" to 0xFF9F422A,
        "flying" to 0xFF90B1C5,
        "poison" to 0xFF642785,
        "ground" to 0xFFAD7235,
        "rock" to 0xFF4B190E,
        "bug" to 0xFF179A55,
        "ghost" to 0xFF363069,
        "steel" to 0xFF5C756D,
        "fire" to 0xFFB22328,
        "water" to 0xFF2648DC,
        "grass" to 0xFF007C42,
        "electric" to 0xFFE0E64B,
        "psychic" to 0xFFAC296B,
        "ice" to 0xFF7ECFF2,
        "dragon" to 0xFF378A94,
        "fairy" to 0xFF9E1A44,
        "dark" to 0xFF040706,
        "gray_21" to 0xFFB1A5A5
    )

    fun getTypeColor(type: String): Long {
        return typeColors[type] ?: typeColors["gray_21"]!!
    }
}