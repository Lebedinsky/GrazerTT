package com.romkaleb.grazertt.data

import com.romkaleb.grazertt.data.local.AuthPreferences
import com.romkaleb.grazertt.data.remote.ApiService
import com.romkaleb.grazertt.data.remote.request.LoginRequest
import com.romkaleb.grazertt.domain.repository.IAuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
): IAuthRepository {

    override suspend fun logIn(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val response = apiService.logIn(LoginRequest(email, password))
            response.auth?.data?.token?.let {
                authPreferences.storeAuthToken(it)
                Result.success(Unit)
            } ?: Result.failure(Exception("Empty Auth response"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}