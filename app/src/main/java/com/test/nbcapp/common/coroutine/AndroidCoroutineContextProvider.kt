package com.test.nbcapp.common.coroutine

import com.test.nbcapp.di.DefaultDispatcher
import com.test.nbcapp.di.IoDispatcher
import com.test.nbcapp.di.MainDispatcher
import com.test.nbcapp.di.MainImmediateDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AndroidCoroutineContextProvider @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    @MainImmediateDispatcher immediateDispatcher: CoroutineDispatcher
) : CoroutineContextProvider() {
    override val Main: CoroutineContext = mainDispatcher
    override val IO: CoroutineContext = ioDispatcher
    override val Default: CoroutineContext = defaultDispatcher
    override val Immediate: CoroutineContext = immediateDispatcher
}
