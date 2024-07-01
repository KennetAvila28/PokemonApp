package com.kennet.pokeapp.data.network

import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.Url
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private val api: PokemonService){
    suspend fun getAll(limit:Int): PokemonResponse?{
        return api.getAllPokemons(limit)
    }
    suspend fun getPokemon(url:String): PokemonDetail?{
        return api.getPokemon(url)
    }
}