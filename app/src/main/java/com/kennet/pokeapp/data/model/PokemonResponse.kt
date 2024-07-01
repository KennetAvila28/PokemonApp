package com.kennet.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count")
    val count: Long,
    @SerializedName("next")
    val next: String,
    @SerializedName("previus")
    val previous: String?,
    @SerializedName("results")
    val results: List<Pokemon>,
)
