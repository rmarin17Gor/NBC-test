package com.test.nbcapp.home.domain.interactor

import com.test.nbcapp.di.IoDispatcher
import com.test.nbcapp.home.data.repository.HomeRepository
import com.test.nbcapp.home.domain.mapper.toListShelfUiModel
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Class that handle the implementation of ShelvesInteractor.
 */
class ShelvesInteractorImpl @Inject constructor(
    private val homeRepositoryImpl: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ShelvesInteractor {

    override suspend fun getShelves(): Map<ShelfSection, MutableList<ShelfUiModel>> {
        return withContext(ioDispatcher) {
            homeRepositoryImpl.getShelvesData().toListShelfUiModel()
        }
    }
}
