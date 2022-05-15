package com.sebasorozcob.www.data.di

import android.app.Application
import androidx.room.Room
import com.sebasorozcob.www.data.local.MovietailDatabase
import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.domain.common.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideMoviesDatabase(app: Application) : MovietailDatabase {
//        return Room.databaseBuilder(
//            app,
//            MovietailDatabase::class.java,
//            DATABASE_NAME
//        ).build()
//    }
}