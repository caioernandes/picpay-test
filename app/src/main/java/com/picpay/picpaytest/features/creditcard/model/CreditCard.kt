package com.picpay.picpaytest.features.creditcard.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CreditCard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cardNumber: String = "",
    val bearer: String = "",
    val cvv: Int = 0,
    val expiryDate: String = ""
): Parcelable