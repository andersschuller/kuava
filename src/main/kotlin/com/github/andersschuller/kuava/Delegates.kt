package com.github.andersschuller.kuava

import com.google.common.cache.Cache
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

object Delegates {
    fun cacheVal<T>(cache: Cache<String, T>) = object : ReadOnlyProperty<Any?, T> {
        override fun get(thisRef: Any?, property: PropertyMetadata): T {
            return cache.getIfPresent(property.name)
        }
    }

    fun cacheVar<T>(cache: Cache<String, T>) = object : ReadWriteProperty<Any?, T> {
        override fun get(thisRef: Any?, property: PropertyMetadata): T {
            return cache.getIfPresent(property.name)
        }
        override fun set(thisRef: Any?, property: PropertyMetadata, value: T) {
            cache.put(property.name, value)
        }
    }
}
