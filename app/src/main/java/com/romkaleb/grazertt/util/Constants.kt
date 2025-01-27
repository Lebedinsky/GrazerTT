package com.romkaleb.grazertt.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.romkaleb.grazertt.data.model.User

object Constants {
    const val BASE_URL = "https://grazer.nw.r.appspot.com/v1/"
    const val AUTH_PREFERENCES_FILE = "AUTH_PREFERENCES_FILE"
    val AUTH_KEY = stringPreferencesKey("AUTH_KEY")

    const val SECOND_IN_MILLIS: Long = 1000
    const val MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60
    const val HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60
    const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
    const val YEAR_IN_MILLIS = DAY_IN_MILLIS * 365

    val stubUserList = listOf(
        User(
            "Roman Lebedinsky ...1...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...2...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...3...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...4...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...5...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...6...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...7...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...8...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...9...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...10...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...11...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        ),
        User(
            "Roman Lebedinsky ...12...",
            732326400,
            "https://this-person-does- not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
        )
    )
}