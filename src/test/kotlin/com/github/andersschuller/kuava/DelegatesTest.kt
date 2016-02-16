package com.github.andersschuller.kuava

import com.google.common.cache.*
import kotlin.test.*
import org.junit.Test

class DelegatesTest {
    val cache = newCache()

    @Test fun delegateValToCache() {
        val example = CacheValExample(cache)
        assertEquals(cache.getIfPresent("foo"), example.foo)
    }

    @Test fun delegateVarToCache() {
        val example = CacheVarExample(cache)
        example.bar = 43
        assertEquals(example.bar, 43)
        assertEquals(cache.getIfPresent("bar"), 43)
    }

    fun newCache(): Cache<String, Int> {
        val cache: Cache<String, Int> = CacheBuilder.newBuilder().build()
        cache.put("foo", 1)
        cache.put("bar", 2)
        return cache
    }

    class CacheValExample(cache: Cache<String, Int>) {
        val foo: Int? by Delegates.cacheVal(cache)
    }

    class CacheVarExample(cache: Cache<String, Int>) {
        var bar: Int? by Delegates.cacheVar(cache)
    }
}
