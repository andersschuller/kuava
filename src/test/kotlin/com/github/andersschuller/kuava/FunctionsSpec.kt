package com.github.andersschuller.kuava

import com.google.common.base.Function as GuavaFunction
import com.google.common.base.Functions as GuavaFunctions
import com.google.common.base.Predicate as GuavaPredicate
import com.google.common.base.Predicates as GuavaPredicates
import com.google.common.base.Supplier as GuavaSupplier
import com.google.common.base.Suppliers as GuavaSuppliers
import org.jetbrains.spek.api.*

class FunctionsSpec : Spek() {
    init {
        given("a function") {
            val f: (String) -> Int = { s -> s.length() }
            on("converting to a Guava Function") {
                val g: GuavaFunction<String, Int> = f.toGuavaFunction()
                it("should return the same result when invoked") {
                    shouldEqual(f("foo"), g.apply("foo"))
                }
            }
        }

        given("a predicate function") {
            val f: (Int) -> Boolean = { i -> i % 2 == 0 }
            on("converting to a Guava Predicate") {
                val g: GuavaPredicate<Int> = f.toGuavaPredicate()
                it("should return the same result when invoked") {
                    shouldEqual(f(42), g.apply(42))
                }
            }
        }

        given("a supplier function") {
            val f: () -> Long = { -> 1024 }
            on("converting to a Guava Supplier") {
                val g: GuavaSupplier<Long> = f.toGuavaSupplier()
                it("should return the same result when invoked") {
                    shouldEqual(f(), g.get())
                }
            }
        }

        given("a Guava Function") {
            val g: GuavaFunction<Any, String> = GuavaFunctions.toStringFunction()
            on("converting to a function") {
                val f: (Any) -> String = g.toFunction()
                it("should return the same result when invoked") {
                    shouldEqual(g.apply(12), f(12))
                }
            }
        }

        given("a Guava Predicate") {
            val g: GuavaPredicate<String> = GuavaPredicates.equalTo("foo")
            on("converting to a function") {
                val f: (String) -> Boolean = g.toFunction()
                it("should return the same result when invoked") {
                    shouldEqual(g.apply("bar"), f("bar"))
                }
            }
        }

        given("a Guava Supplier") {
            val g: GuavaSupplier<Char> = GuavaSuppliers.ofInstance('Y')
            on("converting to a function") {
                val f: () -> Char = g.toFunction()
                it("should return the same result when invoked") {
                    shouldEqual(g.get(), f())
                }
            }
        }
    }
}
