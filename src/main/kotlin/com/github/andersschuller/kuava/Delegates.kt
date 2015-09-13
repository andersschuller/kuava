package com.github.andersschuller.kuava

import com.google.common.cache.Cache
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

object Delegates {
    fun cacheVal<T>(cache: Cache<String, T>) = object : ReadOnlyProperty<Any?, T> {
        override fun get(thisRef: Any?, desc: PropertyMetadata): T {
            return cache.getIfPresent(desc.name)
        }
    }

    fun cacheVar<T>(cache: Cache<String, T>) = object : ReadWriteProperty<Any?, T> {
        override fun get(thisRef: Any?, desc: PropertyMetadata): T {
            return cache.getIfPresent(desc.name)
        }
        override fun set(thisRef: Any?, desc: PropertyMetadata, value: T) {
            cache.put(desc.name, value)
        }
    }
}
