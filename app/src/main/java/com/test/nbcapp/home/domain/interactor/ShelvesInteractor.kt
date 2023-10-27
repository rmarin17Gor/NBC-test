package com.test.nbcapp.home.domain.interactor

import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.ShelfUiModel

/**
 * Interface that handle the common functions related to shelves.
 */
interface ShelvesInteractor {
    suspend fun getShelves(): Map<ShelfSection, MutableList<ShelfUiModel>>
}
