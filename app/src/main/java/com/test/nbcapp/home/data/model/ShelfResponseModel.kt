package com.test.nbcapp.home.data.model

/**
 * Data class that handle the response model to parse from Json related to shelf.
 */
data class ShelfResponseModel(
    val items: List<ItemResponseModel>,
    val title: String,
    val type: String
)
