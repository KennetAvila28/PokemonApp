package com.kennet.pokeapp.domain

import com.kennet.pokeapp.data.model.Pokemon
import com.kennet.pokeapp.data.network.PokemonRepository
import javax.inject.Inject


class GetAllPokemonsUseCase @Inject constructor(private val repository: PokemonRepository){


    suspend operator fun invoke (limit:Int) : List<Pokemon>?{
        return repository.getAll(limit)?.results
    }
}