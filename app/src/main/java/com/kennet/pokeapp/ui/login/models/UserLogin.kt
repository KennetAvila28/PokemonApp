package com.kennet.pokeapp.ui.login.models

data class UserLogin(
    val email: String = "",
    val password: String = "",
    val showErrorDialog: Boolean = false
)