package com.test.nbcapp.common.logger

/**
 * Interface that handle all the functions related to log.
 */
interface Logger {
    fun logMessage(tag: String, message: String, level: Level = Level.DEBUG)
}
