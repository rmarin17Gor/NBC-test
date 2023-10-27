package com.test.nbcapp.home.presentation.viewmodel

import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.ShelfUiModel

/**
 * Sealed class that handle the different view states of home view model.
 */
sealed class HomeViewState {
    object Loading: HomeViewState()
    data class LoadShelves(val shelves: Map<ShelfSection, MutableList<ShelfUiModel>>) : HomeViewState()
    data class Failure(val error: Throwable): HomeViewState()
}
