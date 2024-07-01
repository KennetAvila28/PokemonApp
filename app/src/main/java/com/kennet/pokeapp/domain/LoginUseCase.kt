package com.kennet.pokeapp.domain

import com.kennet.pokeapp.data.network.AuthenticationService
import com.kennet.pokeapp.data.response.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)
}