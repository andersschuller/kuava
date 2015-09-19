package com.github.andersschuller.kuava

import com.google.common.base.Optional
import kotlin.test.*
import org.junit.Test

class OptionalsTest {
    @Test fun convertNonNullableValueToOptional() {
        val s: String = "bar"
        val o: Optional<String> = s.toOptional()
        assertTrue(o.isPresent)
        assertEquals(s, o.get())
        assertEquals(s, o.orNull())
    }

    @Test fun convertNullableValueToOptional() {
        val s: Int? = 42
        val o: Optional<Int> = s.toOptional()
        assertTrue(o.isPresent)
        assertEquals(s, o.get())
        assertEquals(s, o.orNull())
    }

    @Test fun convertNullToOptional() {
        val s: Long? = null
        val o: Optional<Long> = s.toOptional()
        assertFalse(o.isPresent)
        assertEquals(s, o.orNull())
    }
}
