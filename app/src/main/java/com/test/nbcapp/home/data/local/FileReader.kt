package com.test.nbcapp.home.data.local

import android.content.Context
import com.test.nbcapp.common.logger.Level
import com.test.nbcapp.common.logger.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handle the functions and functionality related to read from file.
 */
class FileReader @Inject constructor(
    private val logger: Logger,
    @ApplicationContext private val context: Context
) {

    fun loadJsonDataFromFileAsset(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            logger.logMessage(
                this.javaClass.name,
                ioException.localizedMessage.orEmpty(),
                Level.ERROR
            )
            null
        }
    }
}
