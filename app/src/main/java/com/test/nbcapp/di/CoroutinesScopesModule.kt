package com.test.nbcapp.di

import com.test.nbcapp.common.coroutine.AndroidCoroutineContextProvider
import com.test.nbcapp.common.coroutine.CoroutineContextProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CoroutinesScopesModule {

    @Singleton
    @ApplicationScope
    @Binds
    abstract fun providesCoroutineScope(androidCoroutineContextProvider: AndroidCoroutineContextProvider): CoroutineContextProvider
}
