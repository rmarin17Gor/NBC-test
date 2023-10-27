package com.test.nbcapp.home.domain.mapper

import com.test.nbcapp.home.data.model.ShelvesResponseModel
import com.test.nbcapp.home.domain.model.ShelfSection
import com.test.nbcapp.home.domain.model.ShelfSection.CONTINUE_WATCHING
import com.test.nbcapp.home.domain.model.ShelfSection.LATEST_EPISODE
import com.test.nbcapp.home.domain.model.ShelfSection.TRENDING_NOW
import com.test.nbcapp.home.domain.model.ShelfType
import com.test.nbcapp.home.domain.model.ShelfType.EPISODE
import com.test.nbcapp.home.domain.model.ShelfType.LIVE
import com.test.nbcapp.home.domain.model.ShelfType.SHOW
import com.test.nbcapp.home.presentation.model.EpisodeUiModel
import com.test.nbcapp.home.presentation.model.LiveUiModel
import com.test.nbcapp.home.presentation.model.ShelfUiModel
import com.test.nbcapp.home.presentation.model.ShowUiModel

/**
 * File that contains all the extension functions to convert the data models to ui models
 * related to shelves.
 */

/**
 * Extension function that converts a ShelvesResponseModel into a list of ShelfUiModel
 */
fun ShelvesResponseModel.toListShelfUiModel(): Map<ShelfSection, MutableList<ShelfUiModel>> {
    val mapShelves = mutableMapOf<ShelfSection, MutableList<ShelfUiModel>>()
    shelves.forEach { shelf ->
        val key = shelf.title.toShelfSection()
        mapShelves.getOrPut(key) { mutableListOf() }.addAll(
            shelf.items.map { item ->
                when (item.type.toShelfType()) {
                    SHOW -> ShowUiModel(
                        item.title,
                        item.image
                    )
                    LIVE -> LiveUiModel(
                        item.title,
                        item.image,
                        item.subtitle.orEmpty()
                    )
                    EPISODE -> EpisodeUiModel(
                        item.title,
                        item.image,
                        item.subtitle.orEmpty(),
                        item.labelBadge.orEmpty()
                    )
                }
            }
        )
    }
    return mapShelves.toMap()
}

/**
 * Extension function to convert string into ShelfType.
 */
fun String.toShelfType(): ShelfType {
    return when (this) {
        SHOW.value -> SHOW
        LIVE.value -> LIVE
        EPISODE.value -> EPISODE
        else -> throw IllegalStateException("Incompatible shelf type: $this to be converted")
    }
}

/**
 * Extension function to convert string into ShelfSection.
 */
fun String.toShelfSection(): ShelfSection {
    return when (this) {
        CONTINUE_WATCHING.value -> CONTINUE_WATCHING
        TRENDING_NOW.value -> TRENDING_NOW
        LATEST_EPISODE.value -> LATEST_EPISODE
        else -> throw IllegalStateException("Incompatible shelf section: $this to be converted")
    }
}
