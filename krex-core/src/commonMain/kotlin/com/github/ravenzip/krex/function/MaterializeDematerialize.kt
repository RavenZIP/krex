package com.github.ravenzip.krex.function

import com.github.ravenzip.krex.data.FlowNotification
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.materialize(): Flow<FlowNotification<T>> =
    this.map<T, FlowNotification<T>> { value -> FlowNotification.Next(value) }
        .catch { error -> emit(FlowNotification.Error(error)) }
        .onCompletion { cause ->
            if (cause == null) {
                emit(FlowNotification.Complete)
            }
        }

fun <T> Flow<FlowNotification<T>>.dematerialize(): Flow<T> =
    this.transform { notification ->
        when (notification) {
            is FlowNotification.Next -> emit(notification.value)
            is FlowNotification.Error -> throw notification.error
            is FlowNotification.Complete -> return@transform
        }
    }
