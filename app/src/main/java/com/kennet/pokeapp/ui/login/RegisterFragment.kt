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
import com.kennet.pokeapp.core.ex.dismissKeyboard
import com.kennet.pokeapp.core.ex.loseFocusAfterAction
import com.kennet.pokeapp.core.ex.onTextChanged
import com.kennet.pokeapp.databinding.FragmentRegisterBinding
import com.kennet.pokeapp.ui.login.models.UserRegister
import com.kennet.pokeapp.ui.verification.VerificationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()
    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initUI()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun initUI() {
        initListeners()
        initObservers()
//        binding.viewBottom.tvFooter.text = span(
//            getString(R.string.signin_footer_unselected),
//            getString(R.string.signin_footer_selected)
//        )
    }

    private fun initListeners() {

        binding.email.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.email.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.email.onTextChanged { onFieldChanged() }

        binding.password.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.password.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.password.onTextChanged { onFieldChanged() }

        binding.confirmPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.confirmPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.confirmPassword.onTextChanged { onFieldChanged() }

//        binding.viewBottom.tvFooter.setOnClickListener { viewModel.onLoginSelected() }

        with(binding) {
            registerButton.setOnClickListener {
                it.dismissKeyboard()
                viewModel.onSignInSelected(
                    UserRegister(
                        email = binding.email.text.toString(),
                        password = binding.password.text.toString(),
                        passwordConfirmation = binding.confirmPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun initObservers() {
        viewModel.navigateToVerifyEmail.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToVerifyEmail()
            }
        }

//        viewModel.navigateToLogin.observe(this) {
//            it.getContentIfNotHandled()?.let {
//                goToLogin()
//            }
//        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }

        viewModel.showErrorDialog.observe(requireActivity()) { showError ->
            if (showError) showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        dialogLauncher.show( ErrorDialog.create(
            title = getString(R.string.signin_error_dialog_title),
            description = getString(R.string.signin_error_dialog_body),
            positiveAction = ErrorDialog.Action(getString(R.string.signin_error_dialog_positive_action)) {
                it.dismiss()
            }
        ), requireActivity())
   }

    private fun updateUI(viewState: SignInViewState) {
        with(binding) {
//            pbLoading.isVisible = viewState.isLoading
//            binding.tilEmail.error =
//                if (viewState.isValidEmail) null else getString(R.string.signin_error_mail)
//            binding.tilNickname.error =
//                if (viewState.isValidNickName) null else getString(R.string.signin_error_nickname)
//            binding.tilRealName.error =
//                if (viewState.isValidRealName) null else getString(R.string.signin_error_realname)
//            binding.tilPassword.error =
//                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
//            binding.tilRepeatPassword.error =
//                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
        }
    }


    private fun goToVerifyEmail() {
        startActivity(VerificationActivity.create(requireActivity()))
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            viewModel.onFieldsChanged(
                UserRegister(
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString(),
                    passwordConfirmation = binding.confirmPassword.text.toString()
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}