package com.romkaleb.grazertt.di

import com.romkaleb.grazertt.data.AuthRepositoryImpl
import com.romkaleb.grazertt.data.UserRepositoryImpl
import com.romkaleb.grazertt.data.local.AuthPreferences
import com.romkaleb.grazertt.data.remote.ApiService
import com.romkaleb.grazertt.domain.repository.IAuthRepository
import com.romkaleb.grazertt.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(
        apiService: ApiService,
        authPreferences: AuthPreferences
    ): IAuthRepository {
        return AuthRepositoryImpl(
            apiService = apiService,
            authPreferences = authPreferences
        )
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        apiService: ApiService
    ): IUserRepository {
        return UserRepositoryImpl(apiService = apiService)
    }
}