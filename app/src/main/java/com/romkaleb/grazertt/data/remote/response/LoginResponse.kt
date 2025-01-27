package com.romkaleb.grazertt.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("auth") val auth: AuthResponse?
): BaseResponse()

data class AuthResponse(
    @SerializedName("data") val data: AuthDataResponse?
)

data class AuthDataResponse(
    @SerializedName("token") val token: String?
)