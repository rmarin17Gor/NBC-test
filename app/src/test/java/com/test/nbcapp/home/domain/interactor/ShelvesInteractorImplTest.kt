package com.test.nbcapp.home.domain.interactor

import com.test.nbcapp.common.TestCoroutineRule
import com.test.nbcapp.home.data.model.ItemResponseModel
import com.test.nbcapp.home.data.model.ShelfResponseModel
import com.test.nbcapp.home.data.model.ShelvesResponseModel
import com.test.nbcapp.home.data.repository.HomeRepository
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.LiveUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Class that handle the unit test of ShelvesInteractorImpl.
 */
@ExperimentalCoroutinesApi
class ShelvesInteractorImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var closeable: AutoCloseable

    private lateinit var interactor: ShelvesInteractor

    @Mock
    private lateinit var homeRepository: HomeRepository

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
        interactor = ShelvesInteractorImpl(
            homeRepository,
            testCoroutineRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        closeable.close()
    }

    @Test
    fun `given a ShelvesResponseModel, when getShelves is called, then check result`() = runTest {
        // GIVEN
        val shelvesResponse = mockShelvesResponseModel()
        Mockito.`when`(homeRepository.getShelvesData()).thenReturn(shelvesResponse)
        // WHEN
        val result = interactor.getShelves()
        // THEN
        assertTrue(result.containsKey(ShelfSection.CONTINUE_WATCHING))
        assertTrue((result[ShelfSection.CONTINUE_WATCHING]?.first() is LiveUiModel))
    }

    @Test(expected = Exception::class)
    fun `given an error, when getShelves is called, then check getShelvesData is called`() =
        runTest {
            // GIVEN
            val error = Exception("Test Error")
            Mockito.`when`(homeRepository.getShelvesData()).thenAnswer { throw error }
            // WHEN
            interactor.getShelves()
            // THEN
            Mockito.verify(homeRepository).getShelvesData()
        }

    private fun mockShelvesResponseModel(): ShelvesResponseModel {
        return ShelvesResponseModel(
            "Shelf",
            listOf(
                ShelfResponseModel(
                    listOf(
                        ItemResponseModel(
                            type = "Live",
                            tagline = "WATCH NBC NEWS NOW LIVE",
                            title = "Hallie Jackson NOW",
                            subtitle = "Live News Streaming",
                            image = "https://img.nbc.com/sites/nbcunbc/files/images/2021/2/04/NBC-News_2048_1152.jpg"
                        )
                    ),
                    "Continue Watching",
                    "Shelf"
                )
            )
        )
    }
}
