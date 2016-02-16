package com.github.andersschuller.kuava

import com.google.common.base.Function as GuavaFunction
import com.google.common.base.Functions as GuavaFunctions
import com.google.common.base.Predicate as GuavaPredicate
import com.google.common.base.Predicates as GuavaPredicates
import com.google.common.base.Supplier as GuavaSupplier
import com.google.common.base.Suppliers as GuavaSuppliers
import kotlin.test.*
import org.junit.Test

class FunctionsTest {
    @Test fun convertFunctionToGuavaFunction() {
        val f: (String) -> Int = { s -> s.length }
        val g: GuavaFunction<String, Int> = f.toGuavaFunction()
        assertEquals(f("foo"), g.apply("foo"))
    }

    @Test fun convertPredicateFunctionToGuavaPredicate() {
        val f: (Int) -> Boolean = { i -> i % 2 == 0 }
        val g: GuavaPredicate<Int> = f.toGuavaPredicate()
        assertEquals(f(42), g.apply(42))
    }

    @Test fun convertSupplierFunctionToGuavaSupplier() {
        val f: () -> Long = { -> 1024 }
        val g: GuavaSupplier<Long> = f.toGuavaSupplier()
        assertEquals(f(), g.get())
    }

    @Test fun convertGuavaFunctionToFunction() {
        val g: GuavaFunction<Any, String> = GuavaFunctions.toStringFunction()
        val f: (Any) -> String? = g.toFunction()
        assertEquals(g.apply(12), f(12))
    }

    @Test fun convertGuavaPredicateToFunction() {
        val g: GuavaPredicate<String> = GuavaPredicates.equalTo("foo")
        val f: (String) -> Boolean = g.toFunction()
        assertEquals(g.apply("bar"), f("bar"))
    }

    @Test fun convertGuavaSupplierToFunction() {
        val g: GuavaSupplier<Char> = GuavaSuppliers.ofInstance('Y')
        val f: () -> Char = g.toFunction()
        assertEquals(g.get(), f())
    }
}
