package com.test.nbcapp.home.data.model

/**
 * Data class that handle the response model to parse from Json related to shelf items.
 */
data class ItemResponseModel(
    val title: String,
    val type: String,
    val image: String,
    val labelBadge: String? = null,
    val subtitle: String? = null,
    val tagline: String? = null
)
