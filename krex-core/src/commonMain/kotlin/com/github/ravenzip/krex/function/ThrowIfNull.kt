package com.github.ravenzip.krex.function

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<T?>.throwIfNull(errorFactory: () -> Throwable): Flow<T> =
    this.transform { value -> if (value == null) throw errorFactory() else emit(value) }
