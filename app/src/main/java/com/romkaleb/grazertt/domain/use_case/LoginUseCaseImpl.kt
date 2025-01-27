package com.romkaleb.grazertt.domain.use_case

import com.romkaleb.grazertt.domain.repository.IAuthRepository
import com.romkaleb.grazertt.presentation.use_case.ILoginUseCase

class LoginUseCaseImpl(
    private val authRepository: IAuthRepository
): ILoginUseCase {

    override suspend fun logIn(
        email: String,
        password: String
    ): Result<Unit> {

        return authRepository.logIn(email, password)
    }
}