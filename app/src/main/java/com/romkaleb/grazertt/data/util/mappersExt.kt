package com.romkaleb.grazertt.data.util

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.data.remote.response.UserResponse

fun UserResponse.mapToUser() = User(
    name = name ?: "",
    dateOfBirth = dateOfBirth ?: 0,
    profileImageUrl = profileImageUrl ?: ""
)