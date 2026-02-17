package com.github.ravenzip.krex.function

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow

fun <T> Flow<T>.ignoreError(): Flow<T> = this.catch { emitAll(emptyFlow()) }

inline fun <reified T> Flow<T>.defaultIfError(recoveryValue: T): Flow<T> =
    this.catch { emit(recoveryValue) }
