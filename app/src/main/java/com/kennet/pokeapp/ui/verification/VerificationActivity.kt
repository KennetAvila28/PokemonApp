package com.kennet.pokeapp.ui.verification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kennet.pokeapp.core.dialog.DialogFragmentLauncher
import com.kennet.pokeapp.core.dialog.LoginSuccessDialog
import com.kennet.pokeapp.core.ex.*
import com.kennet.pokeapp.databinding.ActivityVerificationBinding
import com.kennet.pokeapp.ui.pokemon_list.PokemonActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class VerificationActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, VerificationActivity::class.java)
    }

    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    private lateinit var binding: ActivityVerificationBinding
    private val verificationViewModel: VerificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.btnGoToDetail.setOnClickListener { verificationViewModel.onGoToDetailSelected() }
    }

    private fun initObservers() {
        verificationViewModel.navigateToVerifyAccount.observe(this) {
            it.getContentIfNotHandled()?.let {
                dialogLauncher.show( LoginSuccessDialog.create(), this)
                goToPokemonList()
            }
        }

        verificationViewModel.showContinueButton.observe(this) {
            it.getContentIfNotHandled()?.let {
                binding.btnGoToDetail.isVisible = true
            }
        }
    }

    private fun goToPokemonList(){
        startActivity(PokemonActivity.create(this))
    }
}