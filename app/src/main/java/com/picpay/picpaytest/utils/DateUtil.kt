package com.picpay.picpaytest.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val DATE_FORMAT = "dd/MM/yyyy"
    private const val TIME_FORMAT = "HH:mm:ss"
    private val localeBr = Locale("pt", "BR")

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, localeBr)
        return sdf.format(Date())
    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat(TIME_FORMAT, localeBr)
        return sdf.format(Date())
    }
}