package com.picpay.picpaytest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credit_card_table")
data class CreditCard(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val cardNumber: String,
    val bearer: String,
    val cvv: Int,
    val expiryDate: String
)