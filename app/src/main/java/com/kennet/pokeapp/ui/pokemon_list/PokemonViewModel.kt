package com.kennet.pokeapp.ui.pokemon_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.kennet.pokeapp.data.model.Pokemon
import com.kennet.pokeapp.domain.GetAllPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(private val useCase: GetAllPokemonsUseCase): ViewModel(){
    val list = MutableLiveData<List<Pokemon>?>()
    var isloading = false;
    fun onCreate(limit:Int = 10){
        try {
            isloading = true
            viewModelScope.launch {
                val result = useCase(limit)
                if (!result.isNullOrEmpty())
                {
                    list.postValue(result)
                }
            }
        } catch (ex: Exception){
            Timber.e(ex)
        } finally {
            isloading = false;
        }
    }


}