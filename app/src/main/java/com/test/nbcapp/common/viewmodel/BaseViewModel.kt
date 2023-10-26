package com.test.nbcapp.common.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.test.nbcapp.common.coroutine.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel(
    val baseContextProvider: CoroutineContextProvider
) : ViewModel(), DefaultLifecycleObserver {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        onErrorAction(throwable)
    }

    val coroutineContext = object : CoroutineContextProvider() {
        override val Main: CoroutineContext by lazy { baseContextProvider.Main + handler }
        override val IO: CoroutineContext by lazy { baseContextProvider.IO + handler }
        override val Default: CoroutineContext by lazy { baseContextProvider.Default + handler }
        override val Immediate: CoroutineContext by lazy { baseContextProvider.Immediate + handler }
    }

    abstract fun onErrorAction(error: Throwable)
}
