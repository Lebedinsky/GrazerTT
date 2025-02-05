package com.romkaleb.grazertt.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.romkaleb.grazertt.data.AuthRepositoryImpl
import com.romkaleb.grazertt.data.UserRepositoryImpl
import com.romkaleb.grazertt.data.local.AuthPreferences
import com.romkaleb.grazertt.data.remote.ApiService
import com.romkaleb.grazertt.domain.repository.IAuthRepository
import com.romkaleb.grazertt.domain.repository.IUserRepository
import com.romkaleb.grazertt.domain.use_case.GetUserListUseCaseImpl
import com.romkaleb.grazertt.domain.use_case.LoginUseCaseImpl
import com.romkaleb.grazertt.presentation.use_case.IGetUserListUseCase
import com.romkaleb.grazertt.presentation.use_case.ILoginUseCase
import com.romkaleb.grazertt.util.Constants.AUTH_PREFERENCES_FILE
import com.romkaleb.grazertt.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context) : DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(AUTH_PREFERENCES_FILE) }
        )

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) =
        AuthPreferences(dataStore)

    @Provides
    @Singleton
    fun providesApiService(authPreferences: AuthPreferences): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = BODY
        }
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                val authToken = runBlocking {
                    authPreferences.getAuthToken().first()
                }
                if (authToken.isNotBlank()) {
                    requestBuilder.addHeader("Authorization", "Bearer $authToken")
                }
                chain.proceed(requestBuilder.build())
            })
        }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: IAuthRepository): ILoginUseCase {
        return LoginUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesGetUserListUseCase(repository: IUserRepository): IGetUserListUseCase {
        return GetUserListUseCaseImpl(repository)
    }
}
