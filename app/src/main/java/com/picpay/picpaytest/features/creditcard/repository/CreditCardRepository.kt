package com.picpay.picpaytest.features.creditcard.repository

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.creditcard.model.CreditCardInsert

interface CreditCardRepository {
    suspend fun getCreditCard(cardNumber: String): LiveData<CreditCard>
    suspend fun insertCreditCard(creditCard: CreditCardInsert): Long
}