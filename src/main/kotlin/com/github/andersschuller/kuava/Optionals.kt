package com.github.andersschuller.kuava

import com.google.common.base.Optional

@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
fun <T> T?.toOptional(): Optional<T> = Optional.fromNullable(this)
