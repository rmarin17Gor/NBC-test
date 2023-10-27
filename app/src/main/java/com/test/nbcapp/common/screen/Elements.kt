package com.test.nbcapp.common.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import coil.compose.SubcomposeAsyncImage
import com.test.nbcapp.R
import com.test.nbcapp.presentation.ui.theme.AppColors
import com.test.nbcapp.presentation.ui.theme.Dimens

@Composable
fun NBCLogo(modifier: Modifier) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_nbc_logo),
        contentDescription = null,
        tint = AppColors.Background_Search,
        modifier = modifier.size(Dimens.grid_6)
    )
}

@Composable
fun ShelfTitle(modifier: Modifier, titleText: String) {
    Text(
        modifier = modifier.widthIn(Dimens.plane_0, Dimens.plane_240),
        text = titleText,
        color = AppColors.Text_Description,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 2
    )
}

@Composable
fun ShelfSubTitle(modifier: Modifier, subTitleText: String) {
    Text(
        modifier = modifier.widthIn(Dimens.plane_0, Dimens.plane_240),
        text = subTitleText,
        color = AppColors.Text_Description,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun LandScapeImage(modifier: Modifier, imageUrl: String) {
    SubcomposeAsyncImage(
        model = imageUrl,
        loading = { FitCircularProgressIndicator(modifier) },
        contentDescription = null,
        modifier = modifier.size(Dimens.plane_300, Dimens.plane_200),
        alignment = Alignment.CenterStart
    )
}

@Composable
fun PortraitImage(modifier: Modifier, imageUrl: String) {
    SubcomposeAsyncImage(
        model = imageUrl,
        loading = { FitCircularProgressIndicator(modifier) },
        contentDescription = null,
        modifier = modifier.size(Dimens.plane_200, Dimens.plane_300),
        alignment = Alignment.CenterStart
    )
}

@Composable
fun FitCircularProgressIndicator(modifier: Modifier, size: Dp = Dimens.grid_5) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = modifier.size(size),
            color = AppColors.Progress_Indicator
        )
    }
}
