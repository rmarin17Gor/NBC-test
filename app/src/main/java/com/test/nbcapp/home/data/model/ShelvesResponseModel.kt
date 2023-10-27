package com.test.nbcapp.home.data.model

/**
 * Data class that handle the response model to parse from Json related to shelves.
 */
data class ShelvesResponseModel(
    val page: String,
    val shelves: List<ShelfResponseModel>
)
