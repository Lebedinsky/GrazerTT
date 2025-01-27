package com.romkaleb.grazertt.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romkaleb.grazertt.presentation.ui_state.AuthScreenUIState
import com.romkaleb.grazertt.presentation.use_case.ILoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthScreenUIState())
    val uiState: StateFlow<AuthScreenUIState> = _uiState.asStateFlow()

    fun setEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            val loginResult = loginUseCase.logIn(uiState.value.email, uiState.value.password)
            _uiState.value = uiState.value.copy(isLoading = false)

            when {
                loginResult.isSuccess -> {
                    _uiState.value = uiState.value.copy(isSignedIn = true)
                }
                loginResult.isFailure -> {
                    _uiState.value = uiState.value.copy(errorMessage = loginResult.exceptionOrNull()?.message?: "")
                }
            }
        }
    }
}