package com.test.nbcapp.home.presentation.model

/**
 * Data class that handle the required information to show on ui related to shows.
 */
data class ShowUiModel(
    override val title: String,
    override val imageUrl: String
) : ShelfUiModel
