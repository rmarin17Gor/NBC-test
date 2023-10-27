package com.test.nbcapp.di

import com.google.gson.Gson
import com.test.nbcapp.common.logger.Logger
import com.test.nbcapp.common.logger.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideLogger(loggerImpl: LoggerImpl): Logger = loggerImpl
}
