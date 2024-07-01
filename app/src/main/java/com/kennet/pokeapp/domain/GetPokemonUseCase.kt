package com.kennet.pokeapp.domain

import com.kennet.pokeapp.data.model.Pokemon
import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.data.network.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke (url:String) : PokemonDetail?{
        return repository.getPokemon(url)
    }
}