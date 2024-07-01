package com.kennet.pokeapp.data.network

import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.data.model.PokemonResponse
import com.kennet.pokeapp.di.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonService @Inject constructor(private val client: PokemonClient){
    suspend fun getAllPokemons(limit:Int): PokemonResponse?{
        return withContext(Dispatchers.IO){
            val response = client.getAll(limit)
            response.body()
        }
    }
    suspend fun getPokemon(url:String): PokemonDetail?{
        return withContext(Dispatchers.IO){
            val response = client.getPokemon(url)
            response.body()
        }
    }
}