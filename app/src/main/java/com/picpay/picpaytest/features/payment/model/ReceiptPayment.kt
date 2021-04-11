package com.picpay.picpaytest.features.payment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceiptPayment(
    var userAvatar: String = "",
    var userName: String = "",
    var currentDate: String = "",
    var currentHour: String = "",
    var transactionNumber: String = "",
    var numberCreditCard: String = "",
    var valueCreditCardPaid: String = "",
    var totalAmountPaid: String = ""
): Parcelable