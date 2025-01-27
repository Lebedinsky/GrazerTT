package com.romkaleb.grazertt.data.remote

import com.romkaleb.grazertt.data.remote.request.LoginRequest
import com.romkaleb.grazertt.data.remote.response.LoginResponse
import com.romkaleb.grazertt.data.remote.response.UserListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun logIn(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("users")
    suspend fun getUserList(): UserListResponse

}