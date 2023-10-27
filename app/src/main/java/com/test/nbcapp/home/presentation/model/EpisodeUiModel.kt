package com.test.nbcapp.home.presentation.model

/**
 * Data class that handle the required information to show on ui related to episodes.
 */
data class EpisodeUiModel(
    override val title: String,
    override val imageUrl: String,
    val subTitle: String,
    val labelBadge: String
) : ShelfUiModel
