package com.kennet.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("types") val types: ArrayList<Type> = arrayListOf(),
    @SerializedName("abilities") val abilities: ArrayList<Ability> = arrayListOf(),
    @SerializedName("moves") val moves: ArrayList<Move> = arrayListOf(),
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("weight") val weight: Double
)

data class Ability(
    @SerializedName("ability") val details: Details
)

data class Details(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class Move(
    @SerializedName("move") val details: Details
)

data class Type(
    @SerializedName("type") val details: Details
)

data class Sprites(
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_female") var backFemale: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
    @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
)