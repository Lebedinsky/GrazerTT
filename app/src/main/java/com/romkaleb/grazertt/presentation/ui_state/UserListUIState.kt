package com.romkaleb.grazertt.presentation.ui_state

import com.romkaleb.grazertt.data.model.User

data class UserListUIState(
    val userList: List<User> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
