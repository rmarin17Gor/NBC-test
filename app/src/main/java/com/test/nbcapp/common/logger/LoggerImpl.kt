package com.test.nbcapp.common.logger

import android.util.Log
import javax.inject.Inject

/**
 * Class that handle the implementation of Logger interface.
 */
class LoggerImpl @Inject constructor() : Logger {

    override fun logMessage(tag: String, message: String, level: Level) {
        when (level) {
            Level.VERBOSE -> Log.v(tag, message)
            Level.DEBUG -> Log.d(tag, message)
            Level.INFO -> Log.i(tag, message)
            Level.WARN -> Log.w(tag, message)
            Level.ERROR -> Log.e(tag, message)
            Level.ASSERT -> Log.wtf(tag, message)
        }
    }
}
