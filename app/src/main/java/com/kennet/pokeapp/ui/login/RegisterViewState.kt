package com.kennet.pokeapp.ui.login
data class SignInViewState(
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isValidRealName: Boolean = true,
    val isValidNickName: Boolean = true
){
    fun userValidated() = isValidEmail && isValidPassword && isValidRealName && isValidNickName
}