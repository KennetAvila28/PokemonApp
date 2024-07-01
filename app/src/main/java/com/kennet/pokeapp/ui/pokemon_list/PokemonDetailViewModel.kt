package com.kennet.pokeapp.ui.pokemon_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.domain.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val useCase:GetPokemonUseCase):ViewModel() {
    val pokemon =MutableLiveData<PokemonDetail?>()
    fun onCreate(url:String){
        try {
            //isloading = true
            viewModelScope.launch {
                val result = useCase(url)
                if (result != null)
                {
                    pokemon.postValue(result)
                }
            }
        } catch (ex: Exception){
            Timber.e(ex)
        } finally {
           // isloading = false;
        }
    }
}