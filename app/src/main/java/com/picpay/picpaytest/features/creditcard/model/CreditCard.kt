package com.picpay.picpaytest.features.creditcard.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCard(
    val id: Long = (0..10).random().toLong(),
    val cardNumber: String = "",
    val bearer: String = "",
    val cvv: Int = 0,
    val expiryDate: String = ""
): Parcelable