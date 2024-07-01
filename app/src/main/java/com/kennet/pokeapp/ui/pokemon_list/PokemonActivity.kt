package com.kennet.pokeapp.ui.pokemon_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennet.pokeapp.R
import com.kennet.pokeapp.core.dialog.DialogFragmentLauncher
import com.kennet.pokeapp.core.dialog.LoginSuccessDialog
import com.kennet.pokeapp.data.adapter.PokemonAdapter
import com.kennet.pokeapp.data.network.FirebaseClient
import com.kennet.pokeapp.databinding.ActivityPokemonBinding
import com.kennet.pokeapp.ui.verification.VerificationActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, PokemonActivity::class.java)
    }

    private lateinit var binding: ActivityPokemonBinding
    private val pokemonViewModel: PokemonViewModel by viewModels()

    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    @Inject
    lateinit var firebaseauth: FirebaseClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        val recyclerView = binding.list
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        var limit = 10

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount: Int = layoutManager.getItemCount()
                val visibleItemCount: Int = layoutManager.childCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                if (!pokemonViewModel.isloading && totalItemCount <= (firstVisibleItemPosition + visibleItemCount)) {
                    limit += 10
                    pokemonViewModel.onCreate(limit)
                }
            }
        })
        val adapter = PokemonAdapter(emptyList())
        recyclerView.adapter = adapter

        pokemonViewModel.list.observe(this) {
            adapter.updateData(it ?: emptyList())
        }

        val user = firebaseauth.auth.currentUser
        if (user != null) {
            dialogLauncher.show(
                LoginSuccessDialog.create("Bienvenido ${user.displayName ?: "Usuario"}"),
                this
            )
        }
        pokemonViewModel.onCreate()
        binding.logout.setOnClickListener {
            firebaseauth.auth.signOut()
            finish()
        }
    }
}