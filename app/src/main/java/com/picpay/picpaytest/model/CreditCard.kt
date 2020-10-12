package com.picpay.picpaytest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "credit_card_table")
data class CreditCard(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val cardNumber: String,
    val bearer: String,
    val cvv: Int,
    val expiryDate: String
): Parcelable