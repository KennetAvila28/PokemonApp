package com.kennet.pokeapp.data.adapter


import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kennet.pokeapp.R
import com.kennet.pokeapp.data.model.Pokemon
import com.kennet.pokeapp.ui.pokemon_list.PokemonDetailActivity

class PokemonViewHolder(view: View) : ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.tvName)
    val image = view.findViewById<ImageView>(R.id.ivImage)
    val card = view.findViewById<RelativeLayout>(R.id.card)
    fun render(pokemon: Pokemon, position:Int) {
        name.text = pokemon.name
        Glide.with(image.context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position+1}.png").into(image)
        itemView.rootView.setOnClickListener{
            val context = it.context
            val intent = PokemonDetailActivity.create(context, pokemon.url, pokemon.name)
            context.startActivity(intent)
        }
    }



}
