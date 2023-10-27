package com.test.nbcapp.home.data.repository

import com.test.nbcapp.home.data.model.ShelvesResponseModel

/**
 * Interface that handle the common functions related to the home repository.
 */
interface HomeRepository {
    suspend fun getShelvesData(): ShelvesResponseModel
}
