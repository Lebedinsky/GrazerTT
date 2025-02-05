package com.romkaleb.grazertt

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.di.RepositoryModule
import com.romkaleb.grazertt.domain.repository.IAuthRepository
import com.romkaleb.grazertt.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
@Module
object FakeRepositoryModule {

    @Singleton
    @Provides
    fun provideFakeAuthRepository() = object : IAuthRepository {
        override suspend fun logIn(email: String, password: String): Result<Unit> {
            return Result.success(Unit)
        }
    }

    @Singleton
    @Provides
    fun provideFakeUserRepository() = object : IUserRepository {
        override suspend fun getUsers(): Result<List<User>> {
            return Result.success(listOf(
                User("Fake User", 12345678, "photo_url")
            ))
        }
    }
}