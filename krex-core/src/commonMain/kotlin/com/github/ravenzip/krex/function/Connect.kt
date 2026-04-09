package com.github.ravenzip.krex.function

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class ConnectedFlow<T>
internal constructor(
    internal val source: MutableStateFlow<T>,
    internal val flows: MutableList<Flow<T>> = mutableListOf(),
)

fun <T> MutableStateFlow<T>.with(
    flow: Flow<T>,
    update: ((previous: T, current: T) -> T) = { _, current -> current },
): ConnectedFlow<T> {
    val flowWithUpdateSource = flow.onEach { current ->
        this.update { previous -> update(previous, current) }
    }
    val connectedFlow = ConnectedFlow(this)
    connectedFlow.flows.add(flowWithUpdateSource)

    return connectedFlow
}

fun <T> ConnectedFlow<T>.with(
    flow: Flow<T>,
    update: ((previous: T, current: T) -> T) = { _, current -> current },
): ConnectedFlow<T> {
    val flowWithUpdateSource = flow.onEach { current ->
        this.source.update { previous -> update(previous, current) }
    }
    this.flows.add(flowWithUpdateSource)

    return this
}

fun <T> ConnectedFlow<T>.launchIn(scope: CoroutineScope): Job =
    merge(*this.flows.toTypedArray()).launchIn(scope)

suspend fun <T> ConnectedFlow<T>.collect(): Unit = merge(*this.flows.toTypedArray()).collect()
