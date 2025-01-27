package com.romkaleb.grazertt.data

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.data.remote.ApiService
import com.romkaleb.grazertt.data.util.mapToUser
import com.romkaleb.grazertt.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val apiService: ApiService
): IUserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val response = apiService.getUserList()
            response.data?.users?.map { it.mapToUser() }?.let {
                Result.success(it)
            } ?: Result.failure(Exception("Empty users list response"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}