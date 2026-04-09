package com.github.ravenzip.krex.function

import kotlinx.coroutines.flow.*

fun <T> Flow<T>.trackActivity(
    counter: MutableStateFlow<Int>,
    incrementValue: Int = 1,
    decrementValue: Int = 1,
): Flow<T> =
    this.onStart { counter.update { current -> current + incrementValue } }
        .onCompletion { counter.update { current -> current - decrementValue } }
