package com.sebasorozcob.www.data.di

import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.data.repository.CreditsRepositoryImpl
import com.sebasorozcob.www.data.repository.MoviesRepositoryImpl
import com.sebasorozcob.www.domain.common.Constants.BASE_URL
import com.sebasorozcob.www.domain.repository.CreditsRepository
import com.sebasorozcob.www.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideMovietailApi(): MovietailApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovietailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MovietailApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCreditsRepository(api: MovietailApi): CreditsRepository {
        return CreditsRepositoryImpl(api)
    }
}