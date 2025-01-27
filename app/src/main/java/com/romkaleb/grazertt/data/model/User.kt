package com.romkaleb.grazertt.data.model

import com.romkaleb.grazertt.util.Constants.SECOND_IN_MILLIS
import com.romkaleb.grazertt.util.Constants.YEAR_IN_MILLIS

data class User(
    val name: String,
    val dateOfBirth: Long = 0,
    val profileImageUrl: String = "",
) {

    fun getAge(): Int = ((System.currentTimeMillis() - dateOfBirth * SECOND_IN_MILLIS) / YEAR_IN_MILLIS).toInt()
}
