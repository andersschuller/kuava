package com.github.andersschuller.kuava

import com.google.common.cache.Cache
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object Delegates {
    fun <T> cacheVal(cache: Cache<String, T>) = object : ReadOnlyProperty<Any?, T?> {
        override operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return cache.getIfPresent(property.name)
        }
    }

    fun <T> cacheVar(cache: Cache<String, T>) = object : ReadWriteProperty<Any?, T?> {
        override operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return cache.getIfPresent(property.name)
        }
        override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            cache.put(property.name, value)
        }
    }
}
