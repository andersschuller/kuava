package com.github.andersschuller.kuava

import com.google.common.cache.*
import org.jetbrains.spek.api.*

class DelegatesSpec : Spek() {
    init {
        given("a cache containing some data") {
            val cache = newCache()
            on("delegating a val to the cache") {
                val example = CacheValExample(cache)
                it("should return the cached value") {
                    shouldEqual(cache.getIfPresent("foo"), example.foo)
                }
            }

            on("delegating a var to the cache") {
                val example = CacheVarExample(cache)
                it("should allow setting the value and return the cached value") {
                    example.bar = 43
                    shouldEqual(example.bar, 43)
                    shouldEqual(cache.getIfPresent("bar"), 43)
                }
            }
        }
    }

    fun newCache(): Cache<String, Int> {
        val cache: Cache<String, Int> = CacheBuilder.newBuilder().build()
        cache.put("foo", 1)
        cache.put("bar", 2)
        return cache
    }

    class CacheValExample(cache: Cache<String, Int>) {
        val foo: Int by Delegates.cacheVal(cache)
    }

    class CacheVarExample(cache: Cache<String, Int>) {
        var bar: Int by Delegates.cacheVar(cache)
    }
}
