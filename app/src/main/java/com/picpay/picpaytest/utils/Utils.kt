package com.picpay.picpaytest.utils

import java.util.*

fun rand(from: Int, to: Int) : Int {
    val random = Random()
    return random.nextInt(to - from) + from
}