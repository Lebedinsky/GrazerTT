package com.romkaleb.grazertt.presentation.ui_state

data class AuthScreenUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val errorMessage: String = ""
)
