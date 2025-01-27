package com.romkaleb.grazertt.domain.repository

import com.romkaleb.grazertt.data.model.User

interface IUserRepository {

    suspend fun getUsers(): Result<List<User>>
}