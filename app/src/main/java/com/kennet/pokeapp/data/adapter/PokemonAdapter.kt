package com.kennet.pokeapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kennet.pokeapp.R
import com.kennet.pokeapp.data.model.Pokemon

class PokemonAdapter(private var pokemonList:List<Pokemon>): RecyclerView.Adapter<PokemonViewHolder>() {
//    inner class PokemonViewHolder(val binding:PokemonLayoutAdapterBinding):RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflate.inflate(R.layout.pokemon_card, parent, false))
    }

    override fun getItemCount(): Int = pokemonList.size
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]

        holder.render(item, position)

    }

    fun updateData(newList: List<Pokemon>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}