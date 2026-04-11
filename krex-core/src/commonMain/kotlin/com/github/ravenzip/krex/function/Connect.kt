package com.github.ravenzip.krex.function

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class ConnectedFlow<T>
internal constructor(
    private val source: MutableStateFlow<T>,
    private val flows: List<Flow<T>> = emptyList(),
) {
    fun with(
        flow: Flow<T>,
        update: ((previous: T, current: T) -> T) = { _, current -> current },
    ): ConnectedFlow<T> {
        val flowWithUpdateSource = createFlowWithUpdateSource(source, flow, update)
        val connectedFlow = ConnectedFlow(source, flows + flowWithUpdateSource)

        return connectedFlow
    }

    fun launchIn(scope: CoroutineScope): Job = flows.merge().launchIn(scope)

    suspend fun collect() = flows.merge().collect()
}

internal fun <T> createFlowWithUpdateSource(
    source: MutableStateFlow<T>,
    flow: Flow<T>,
    update: ((previous: T, current: T) -> T) = { _, current -> current },
) = flow.onEach { current -> source.update { previous -> update(previous, current) } }

fun <T> MutableStateFlow<T>.connectWith(
    flow: Flow<T>,
    update: ((previous: T, current: T) -> T) = { _, current -> current },
): ConnectedFlow<T> {
    val flowWithUpdateSource = createFlowWithUpdateSource(this, flow, update)
    val connectedFlow = ConnectedFlow(this, listOf(flowWithUpdateSource))

    return connectedFlow
}
