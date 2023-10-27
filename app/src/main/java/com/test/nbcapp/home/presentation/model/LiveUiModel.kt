package com.test.nbcapp.home.presentation.model

/**
 * Data class that handle the required information to show on ui related to lives.
 */
data class LiveUiModel(
    override val title: String,
    override val imageUrl: String,
    val subTitle: String
): ShelfUiModel
