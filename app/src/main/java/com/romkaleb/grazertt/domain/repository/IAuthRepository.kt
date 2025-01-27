package com.romkaleb.grazertt.domain.repository

interface IAuthRepository {

    suspend fun logIn(
        email: String,
        password: String
    ): Result<Unit>
}