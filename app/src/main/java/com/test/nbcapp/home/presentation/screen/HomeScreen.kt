package com.test.nbcapp.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.nbcapp.R
import com.test.nbcapp.common.extension.observeLifecycle
import com.test.nbcapp.common.screen.LoadingScreen
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel
import com.test.nbcapp.home.presentation.viewmodel.HomeViewModel
import com.test.nbcapp.home.presentation.viewmodel.HomeViewState
import com.test.nbcapp.presentation.ui.theme.AppColors
import com.test.nbcapp.presentation.ui.theme.Dimens
import com.test.nbcapp.presentation.ui.theme.NBCAppTheme

/**
 * Composable function in charge of display the Home screen of NBC app and the different states.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    homeViewModel.observeLifecycle(LocalLifecycleOwner.current.lifecycle)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppColors.Background_Gradient_Top,
                        AppColors.Background_Gradient_Bottom
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()
        when (viewState) {
            is HomeViewState.Loading -> LoadingScreen(modifier)
            is HomeViewState.LoadShelves -> RenderHomeScreen(
                modifier,
                (viewState as? HomeViewState.LoadShelves)?.shelves
            )
        }
    }
}

/**
 * Composable function in charge of render the state of home screen with shelves.
 */
@Composable
fun RenderHomeScreen(
    modifier: Modifier,
    shelves: Map<ShelfSection, MutableList<ShelfUiModel>>?
) {
    Column(
        modifier = modifier
            .padding(Dimens.grid_2)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        shelves?.keys?.forEach { title ->
            shelves[title].takeIf { !it.isNullOrEmpty() }?.let { shelf ->
                RenderShelf(
                    modifier,
                    when (title) {
                        ShelfSection.CONTINUE_WATCHING -> stringResource(id = R.string.home_screen_continue_watching_title)
                        ShelfSection.TRENDING_NOW -> stringResource(id = R.string.home_screen_trending_now_title)
                        ShelfSection.LATEST_EPISODE -> stringResource(id = R.string.home_screen_latest_episodes_title)
                    },
                    shelf
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val map = mutableMapOf<ShelfSection, MutableList<ShelfUiModel>>()
    map[ShelfSection.CONTINUE_WATCHING] = mutableListOf(
        ShowUiModel(
            title = "title text",
            imageUrl = "www.image.com"
        )
    )
    NBCAppTheme {
        RenderHomeScreen(
            Modifier,
            map
        )
    }
}
