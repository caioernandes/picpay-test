package com.picpay.picpaytest.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun String.parseToBigDecimal(locale: Locale? = null): BigDecimal {
    return if (this.isNotEmpty()) {
        val localeOrDefault = locale ?: Locale("pt", "BR")
        val replaceable =
            String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance(localeOrDefault).currency.symbol)
        val cleanString = this.replace(replaceable.toRegex(), "")
        BigDecimal(cleanString).setScale(
            2, BigDecimal.ROUND_FLOOR
        ).divide(
            BigDecimal(100), BigDecimal.ROUND_FLOOR
        )
    } else {
        BigDecimal.ZERO
    }
}