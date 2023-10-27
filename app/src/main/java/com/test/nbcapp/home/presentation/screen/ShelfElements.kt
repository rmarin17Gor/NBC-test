package com.test.nbcapp.home.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.test.nbcapp.common.screen.LandScapeImage
import com.test.nbcapp.common.screen.NBCLogo
import com.test.nbcapp.common.screen.PortraitImage
import com.test.nbcapp.common.screen.ShelfSubTitle
import com.test.nbcapp.common.screen.ShelfTitle
import com.test.nbcapp.home.presentation.model.EpisodeUiModel
import com.test.nbcapp.home.presentation.model.LiveUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel
import com.test.nbcapp.presentation.ui.theme.AppColors
import com.test.nbcapp.presentation.ui.theme.Dimens

/**
 * Composable function in charge of display the Show Element.
 */
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
            Spacer(modifier = modifier.weight(1f).fillMaxHeight())
            NBCLogo(modifier)
        }
    }
}

/**
 * Composable function in charge of display the Live Element.
 */
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
            Spacer(modifier = modifier.weight(1f).fillMaxHeight())
            NBCLogo(modifier)
        }
    }
}

/**
 * Composable function in charge of display the Episode Element.
 */
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
                Spacer(modifier = modifier.weight(1f).fillMaxHeight())
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
