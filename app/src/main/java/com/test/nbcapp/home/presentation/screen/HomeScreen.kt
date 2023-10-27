package com.test.nbcapp.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.nbcapp.R
import com.test.nbcapp.common.extension.observeLifecycle
import com.test.nbcapp.common.screen.LandScapeImage
import com.test.nbcapp.common.screen.LoadingScreen
import com.test.nbcapp.common.screen.NBCLogo
import com.test.nbcapp.common.screen.PortraitImage
import com.test.nbcapp.common.screen.ShelfSubTitle
import com.test.nbcapp.common.screen.ShelfTitle
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.presentation.model.EpisodeUiModel
import com.test.nbcapp.home.presentation.model.LiveUiModel
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel
import com.test.nbcapp.home.presentation.viewmodel.HomeViewModel
import com.test.nbcapp.home.presentation.viewmodel.HomeViewState
import com.test.nbcapp.presentation.ui.theme.AppColors
import com.test.nbcapp.presentation.ui.theme.Dimens

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
                        AppColors.Background_Gradient_Bottom,
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()
        when (viewState) {
            is HomeViewState.Loading -> LoadingScreen(modifier)
            is HomeViewState.Failure -> Unit
            is HomeViewState.LoadShelves -> RenderHomeScreen(
                modifier,
                (viewState as? HomeViewState.LoadShelves)?.shelves
            )
        }
    }
}

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
            when (title) {
                ShelfSection.CONTINUE_WATCHING -> RenderShelf(
                    modifier,
                    stringResource(id = R.string.home_screen_continue_watching_title),
                    shelves[title] ?: mutableListOf()
                )

                ShelfSection.TRENDING_NOW -> RenderShelf(
                    modifier,
                    stringResource(id = R.string.home_screen_trending_now_title),
                    shelves[title] ?: mutableListOf()
                )

                ShelfSection.LATEST_EPISODE -> RenderShelf(
                    modifier,
                    stringResource(id = R.string.home_screen_latest_episodes_title),
                    shelves[title] ?: mutableListOf()
                )
            }
        }
    }

}

@Composable
fun RenderShelf(
    modifier: Modifier,
    textTile: String,
    itemList: MutableList<ShelfUiModel>
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = Dimens.grid_2)) {
        Text(
            text = textTile,
            style = MaterialTheme.typography.titleLarge,
            color = AppColors.Text_Title
        )
        Spacer(modifier = modifier.size(Dimens.grid_0_5))
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.grid_2)
        ) {
            items(items = itemList) { item ->
                when (item) {
                    is ShowUiModel -> ShowReviewElement(modifier = modifier, showUiModel = item)
                    is LiveUiModel -> LiveReviewElement(modifier = modifier, liveUiModel = item)
                    is EpisodeUiModel -> EpisodeReviewElement(
                        modifier = modifier,
                        episodeUiModel = item
                    )
                }
            }
        }
    }
}

@Composable
fun ShowReviewElement(
    modifier: Modifier,
    showUiModel: ShowUiModel
) {
    Column(modifier = modifier) {
        PortraitImage(modifier, showUiModel.imageUrl)
        Spacer(modifier = modifier.size(Dimens.grid_1))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShelfTitle(modifier, showUiModel.title)
            Spacer(modifier = modifier.weight(1f))
            NBCLogo(modifier)
        }
    }
}

@Composable
fun LiveReviewElement(
    modifier: Modifier = Modifier,
    liveUiModel: LiveUiModel
) {
    Column(modifier = modifier) {
        LandScapeImage(modifier, liveUiModel.imageUrl)
        Spacer(modifier = modifier.size(Dimens.grid_1))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                ShelfTitle(modifier, liveUiModel.title)
                ShelfSubTitle(modifier, liveUiModel.subTitle)
            }
            Spacer(modifier = modifier.weight(1f))
            NBCLogo(modifier)
        }
    }
}

@Composable
fun EpisodeReviewElement(
    modifier: Modifier,
    episodeUiModel: EpisodeUiModel
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LandScapeImage(modifier, episodeUiModel.imageUrl)
        Spacer(modifier = modifier.size(Dimens.grid_1))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                ShelfTitle(modifier, episodeUiModel.title)
                Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = modifier.padding(Dimens.grid_0_5),
                        text = episodeUiModel.labelBadge,
                        style = TextStyle(background = AppColors.Text_Description)
                    )
                    Spacer(modifier = modifier.size(Dimens.grid_1))
                    ShelfSubTitle(modifier, episodeUiModel.subTitle)
                }
            }
            NBCLogo(modifier)
        }
    }
}
