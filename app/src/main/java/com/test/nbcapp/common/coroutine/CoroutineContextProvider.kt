package com.test.nbcapp.common.coroutine

import kotlin.coroutines.CoroutineContext

abstract class CoroutineContextProvider {
    abstract val Main: CoroutineContext
    abstract val IO: CoroutineContext
    abstract val Default: CoroutineContext
    abstract val Immediate: CoroutineContext
}
