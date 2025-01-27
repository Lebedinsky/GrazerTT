package com.romkaleb.grazertt.presentation.use_case

import com.romkaleb.grazertt.data.model.User

interface IGetUserListUseCase {

    suspend fun getUserList(): Result<List<User>>
}