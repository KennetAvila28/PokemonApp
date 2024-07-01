package com.kennet.pokeapp.domain

import com.kennet.pokeapp.data.network.AuthenticationService
import com.kennet.pokeapp.ui.login.models.UserRegister
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
) {

    suspend operator fun invoke(userSignIn: UserRegister): Boolean {
        return authenticationService.createAccount(userSignIn.email, userSignIn.password) != null
    }
}