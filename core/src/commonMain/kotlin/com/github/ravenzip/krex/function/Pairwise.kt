package com.github.ravenzip.krex.function

import com.github.ravenzip.krex.data.NULL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.pairwise(): Flow<Pair<T, T>> = flow {
    var lastValue: Any? = NULL

    collect { currentValue ->
        if (lastValue !== NULL) {
            emit(Pair(lastValue as T, currentValue))
        }

        lastValue = currentValue
    }
}
