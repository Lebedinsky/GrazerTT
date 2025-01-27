package com.romkaleb.grazertt.presentation.use_case

interface ILoginUseCase {

    suspend fun logIn(
        email: String,
        password: String
    ): Result<Unit>
}