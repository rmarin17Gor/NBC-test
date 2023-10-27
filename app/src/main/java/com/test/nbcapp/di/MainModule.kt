package com.test.nbcapp.di

import com.test.nbcapp.home.data.repository.HomeRepository
import com.test.nbcapp.home.data.repository.HomeRepositoryImpl
import com.test.nbcapp.home.domain.interactor.ShelvesInteractor
import com.test.nbcapp.home.domain.interactor.ShelvesInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindShelvesInteractor(shelvesInteractor: ShelvesInteractorImpl): ShelvesInteractor
}
