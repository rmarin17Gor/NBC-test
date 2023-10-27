package com.test.nbcapp.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.test.nbcapp.home.presentation.model.EpisodeUiModel
import com.test.nbcapp.home.presentation.model.LiveUiModel
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel
import com.test.nbcapp.presentation.ui.theme.AppColors
import com.test.nbcapp.presentation.ui.theme.Dimens

/**
 * Composable function in charge of display the Shelf.
 */
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
