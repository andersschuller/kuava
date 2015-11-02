package com.github.andersschuller.kuava

import com.google.common.base.Optional

fun <T> T?.toOptional(): Optional<T> = Optional.fromNullable(this)
