package com.romkaleb.grazertt.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.presentation.ui_state.UserListUIState
import com.romkaleb.grazertt.presentation.use_case.IGetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class UserListScreenViewModel @Inject constructor(
    private val getUserListUseCase: IGetUserListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UserListUIState())
    val uiState: StateFlow<UserListUIState> = _uiState
        .onStart { loadUserList() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(10000),
            UserListUIState()
        )

    fun loadUserList() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            val userListResult = getUserListUseCase.getUserList()
            _uiState.value = uiState.value.copy(isLoading = false)

            with(userListResult) {
                when {
                    isSuccess -> {
                        _uiState.value = userListResult.getOrNull()?.let { userList ->
                            uiState.value.copy(userList = userList)
                        } ?: uiState.value.copy(errorMessage = "Empty User List")
                    }
                    isFailure -> {
                        _uiState.value = uiState.value.copy(errorMessage = userListResult.exceptionOrNull()?.message?: "")
                    }
                }
            }
        }
    }
}