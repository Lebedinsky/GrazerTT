package com.romkaleb.grazertt.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("data") val data: UserListDataResponse?
): BaseResponse()

data class UserListDataResponse(
    @SerializedName("users") val users: List<UserResponse>?,
    @SerializedName("meta") val metadata: UserListMetadataResponse?
)

data class UserResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("date_of_birth") val dateOfBirth: Long?,
    @SerializedName("profile_image") val profileImageUrl: String?,
)

data class UserListMetadataResponse(
    @SerializedName("item_count") val itemCount: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("current_page") val currentPage: Int?
)
