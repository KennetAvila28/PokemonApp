package com.kennet.pokeapp.data.network

import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonClient {
    @GET("pokemon/")
    suspend fun getAll( @Query("limit") limit: Int):Response<PokemonResponse>
    @GET
    suspend fun getPokemon(@Url url:String):Response<PokemonDetail>
}