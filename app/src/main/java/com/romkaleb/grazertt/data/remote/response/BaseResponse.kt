package com.romkaleb.grazertt.data.remote.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status") val status: Int = 0,
    @SerializedName("status_desc") val statusDesc: String = ""
)
