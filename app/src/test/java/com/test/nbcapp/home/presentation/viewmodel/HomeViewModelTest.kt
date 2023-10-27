package com.test.nbcapp.home.presentation.viewmodel

import com.test.nbcapp.common.TestContextProvider
import com.test.nbcapp.common.TestCoroutineRule
import com.test.nbcapp.common.logger.Level
import com.test.nbcapp.common.logger.Logger
import com.test.nbcapp.home.domain.interactor.ShelvesInteractor
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Class that handle the unit test of HomeViewModel.
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val contextProvider = TestContextProvider(testCoroutineRule.testDispatcher)

    private lateinit var closeable: AutoCloseable

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var shelvesInteractor: ShelvesInteractor

    @Mock
    private lateinit var logger: Logger

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(
            shelvesInteractor,
            logger,
            contextProvider
        )
    }

    @After
    fun teardown() {
        closeable.close()
    }

    @Test
    fun `given a map of shelves, when loadShelves is called, then check last state equals LoadShelves`() = runTest(contextProvider.IO) {
        // GIVEN
        val results = mutableListOf<HomeViewState>()
        val mockShelves = mockShelves()
        `when`(shelvesInteractor.getShelves()).thenReturn(mockShelves)
        val job = launch(contextProvider.IO) { viewModel.viewState.toList(results) }

        // WHEN
        viewModel.loadShelves()
        runCurrent()

        // THEN
        assertTrue(results.last() is HomeViewState.LoadShelves)
        job.cancel()
    }

    @Test
    fun `given an error, when onErrorAction is called, then check logs error message with the Logger`() {
        // GIVEN
        val errorMessage = "Test Error"
        val testError = Exception(errorMessage)

        // WHEN
        viewModel.onErrorAction(testError)

        // THEN
        verify(logger).logMessage(HomeViewModel::class.java.name, errorMessage, Level.ERROR)
    }

    private fun mockShelves(): Map<ShelfSection, MutableList<ShelfUiModel>> {
        val map = mutableMapOf<ShelfSection, MutableList<ShelfUiModel>>()
        val latestEpisodesList = mutableListOf<ShelfUiModel>()
        latestEpisodesList.add(
            ShowUiModel(
                title = "title text",
                imageUrl = "www.image.com"
            )
        )
        map[ShelfSection.LATEST_EPISODE] = latestEpisodesList
        return map
    }
}
