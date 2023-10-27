package com.test.nbcapp.common.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.test.nbcapp.presentation.ui.theme.Dimens

@Composable
fun LoadingScreen(modifier: Modifier) {
    FitCircularProgressIndicator(modifier, Dimens.plane_100)
}
