package com.kennet.pokeapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kennet.pokeapp.R
import com.kennet.pokeapp.core.dialog.DialogFragmentLauncher
import com.kennet.pokeapp.core.dialog.ErrorDialog
import com.kennet.pokeapp.core.dialog.LoginSuccessDialog
import com.kennet.pokeapp.core.ex.dismissKeyboard
import com.kennet.pokeapp.core.ex.loseFocusAfterAction
import com.kennet.pokeapp.core.ex.onTextChanged
import com.kennet.pokeapp.data.network.FirebaseClient
import com.kennet.pokeapp.databinding.FragmentLoginBinding
import com.kennet.pokeapp.ui.login.models.UserLogin
import com.kennet.pokeapp.ui.pokemon_list.PokemonActivity
import com.kennet.pokeapp.ui.verification.VerificationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @Inject
    lateinit var firebaseauth: FirebaseClient
    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initUI()
        return binding.root

    }
    private fun initUI() {

        initListeners()
        initObservers()

    }
    private fun initListeners() {
        if (firebaseauth.auth.currentUser != null){
            goToDetail()
            return
        }
        binding.email.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.email.onTextChanged { onFieldChanged() }

        binding.password.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.password.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.password.onTextChanged { onFieldChanged() }

        binding.loginButton.setOnClickListener {
            it.dismissKeyboard()
            loginViewModel.onLoginSelected(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }
    }

    private fun initObservers() {
        loginViewModel.navigateToDetails.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToDetail()
            }
        }



        loginViewModel.navigateToVerifyAccount.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToVerify()
            }
        }

        loginViewModel.showErrorDialog.observe(viewLifecycleOwner) { userLogin ->
            if (userLogin.showErrorDialog) showErrorDialog(userLogin)
        }


    }


    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            loginViewModel.onFieldsChanged(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
        }
    }
    private fun goToDetail() {

        startActivity(PokemonActivity.create(requireActivity()))
    }
    private fun showErrorDialog(userLogin: UserLogin) {
        dialogLauncher.show( ErrorDialog.create(
            title = getString(R.string.login_error_dialog_title),
            description = getString(R.string.login_error_dialog_body),
            negativeAction = ErrorDialog.Action(getString(R.string.login_error_dialog_negative_action)) {
                it.dismiss()
            },
            positiveAction = ErrorDialog.Action(getString(R.string.login_error_dialog_positive_action)) {
                loginViewModel.onLoginSelected(
                    userLogin.email,
                    userLogin.password
                )
                it.dismiss()
            }
        ), requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    private fun goToVerify() {
        startActivity(VerificationActivity.create(requireActivity()))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}