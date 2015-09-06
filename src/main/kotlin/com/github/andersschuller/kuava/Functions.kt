package com.github.andersschuller.kuava

import com.google.common.base.Function as GuavaFunction
import com.google.common.base.Predicate as GuavaPredicate
import com.google.common.base.Supplier as GuavaSupplier

fun <F, T> Function1<F, T>.toGuavaFunction(): GuavaFunction<F, T> = object : GuavaFunction<F, T> {
    override fun apply(input: F): T = invoke(input)
}

fun <T> Function1<T, Boolean>.toGuavaPredicate(): GuavaPredicate<T> = object : GuavaPredicate<T> {
    override fun apply(input: T): Boolean = invoke(input)
}

fun <T> Function0<T>.toGuavaSupplier(): GuavaSupplier<T> = object : GuavaSupplier<T> {
    override fun get(): T = invoke()
}

fun <F, T> GuavaFunction<F, T>.toFunction(): Function1<F, T> = { apply(it) }

fun <T> GuavaPredicate<T>.toFunction(): Function1<T, Boolean> = { apply(it) }

fun <T> GuavaSupplier<T>.toFunction(): Function0<T> = { get() }
