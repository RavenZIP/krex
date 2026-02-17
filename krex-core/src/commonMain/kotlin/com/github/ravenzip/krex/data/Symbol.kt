package com.github.ravenzip.krex.data

import kotlin.jvm.JvmField

internal class Symbol(@JvmField val symbol: String) {
    override fun toString(): String = "<$symbol>"

    @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
    inline fun <T> unbox(value: Any?): T = if (value === this) null as T else value as T
}
