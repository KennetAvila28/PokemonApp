package com.kennet.pokeapp.ui.pokemon_list

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kennet.pokeapp.R
import com.kennet.pokeapp.core.dialog.DialogFragmentLauncher
import com.kennet.pokeapp.core.dialog.LoginSuccessDialog
import com.kennet.pokeapp.core.utils.PokemonColors
import com.kennet.pokeapp.data.adapter.PokemonAdapter
import com.kennet.pokeapp.data.model.PokemonDetail
import com.kennet.pokeapp.data.network.FirebaseClient
import com.kennet.pokeapp.databinding.ActivityPokemonBinding
import com.kennet.pokeapp.databinding.ActivityPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_POKEMON_URL = "pokemon_url"
        private const val EXTRA_POKEMON_NAME = "pokemon_name"
        fun create(context: Context, url:String, name:String): Intent =
            Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_POKEMON_URL, url)
                putExtra(EXTRA_POKEMON_NAME, name)
            }
    }
    private lateinit var binding: ActivityPokemonDetailBinding
    private  val  pokemonDetailViewModel: PokemonDetailViewModel by viewModels()
    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra(EXTRA_POKEMON_NAME)
            ?.let {  binding.name.text = it.replaceFirstChar { letter -> letter.uppercase() }}
        intent.getStringExtra(EXTRA_POKEMON_URL)
            ?.let { pokemonDetailViewModel.onCreate(it) }
        pokemonDetailViewModel.pokemon.observe(this){ it ->
            Glide.with(binding.ivImage.context).load(it?.sprites?.frontDefault).into(binding.ivImage)
            it?.types?.map { type->
                val button = Button(this)
                button.text = type.details.name
                button.textSize = 20F
                button.setTextColor(resources.getColor(R.color.white))
                val drawable = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 30f // Ajusta el valor según tus necesidades
                    setColor(PokemonColors.getTypeColor(
                        type.details.name!!
                    ).toInt()) // Cambia esto por el color deseado
                }

                // Aplicar el fondo al botón
                button.background = drawable

                button.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = 8
                    marginEnd = 8

                }

                // Agregar OnClickListener si es necesario
                button.setOnClickListener {
                    // Acción al hacer clic en el botón
                }

                binding.buttonContainer.addView(button)
            }
            it?.abilities?.map { ability->
                val tv = TextView(this)
                tv.text = ability.details.name?.replaceFirstChar { letter -> letter.uppercase() }
                tv.textSize = 20F
                binding.abilities.addView(tv)
            }
            it?.moves?.map { movement->
                val tv = TextView(this)
                tv.text = movement.details.name?.replaceFirstChar { letter -> letter.uppercase() }
                tv.textSize = 20F
                binding.movements.addView(tv)
            }
            binding.nAbilities.text = "${it?.abilities?.size} \n Hablidades"
            binding.nMovements.text = "${it?.moves?.size} \n Movimientos"
        }
    }
}