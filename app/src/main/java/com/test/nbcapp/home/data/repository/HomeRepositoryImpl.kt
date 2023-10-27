package com.test.nbcapp.home.data.repository

import com.google.gson.Gson
import com.test.nbcapp.di.IoDispatcher
import com.test.nbcapp.home.data.local.FileReader
import com.test.nbcapp.home.data.model.ShelvesResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that handle the implementation of HomeRepository.
 */
@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val fileReader: FileReader,
    private val gson: Gson,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HomeRepository {

    override suspend fun getShelvesData(): ShelvesResponseModel {
        return withContext(ioDispatcher) {
            val jsonFileString = fileReader.loadJsonDataFromFileAsset(HOME_PAGE_JSON_FILE)
            gson.fromJson(jsonFileString, ShelvesResponseModel::class.java)
        }
    }

    companion object {
        // For now we are reading from a local file, so once integration with API service is done
        // please remove this constant.
        private const val HOME_PAGE_JSON_FILE = "homepage.json"
    }
}
