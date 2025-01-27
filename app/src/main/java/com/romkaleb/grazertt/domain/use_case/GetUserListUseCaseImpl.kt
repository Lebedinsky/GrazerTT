package com.romkaleb.grazertt.domain.use_case

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.domain.repository.IUserRepository
import com.romkaleb.grazertt.presentation.use_case.IGetUserListUseCase

class GetUserListUseCaseImpl(
    private val userRepository: IUserRepository
): IGetUserListUseCase {

    override suspend fun getUserList(): Result<List<User>> {
        return userRepository.getUsers()
    }
}