package com.test.nbcapp.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.test.nbcapp.common.coroutine.CoroutineContextProvider
import com.test.nbcapp.common.logger.Level.ERROR
import com.test.nbcapp.common.logger.Logger
import com.test.nbcapp.common.viewmodel.BaseViewModel
import com.test.nbcapp.di.ApplicationScope
import com.test.nbcapp.home.domain.interactor.ShelvesInteractor
import com.test.nbcapp.home.presentation.viewmodel.HomeViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class that handle the view model of home screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shelvesInteractor: ShelvesInteractor,
    private val logger: Logger,
    @ApplicationScope baseContextProvider: CoroutineContextProvider
) : BaseViewModel(baseContextProvider) {

    private val _viewState = MutableStateFlow<HomeViewState>(Loading)
    val viewState: StateFlow<HomeViewState>
        get() = _viewState.asStateFlow()

    init {
        loadShelves()
    }

    override fun onErrorAction(error: Throwable) {
        logger.logMessage(this.javaClass.name, error.localizedMessage.orEmpty(), ERROR)
    }

    private fun loadShelves() {
        _viewState.value = Loading
        viewModelScope.launch(coroutineContext.IO) {
            val shelves = shelvesInteractor.getShelves()
            _viewState.value = HomeViewState.LoadShelves(shelves)
        }
    }
}
