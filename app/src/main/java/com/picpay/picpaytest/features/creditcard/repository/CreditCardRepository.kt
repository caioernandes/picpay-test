package com.picpay.picpaytest.features.creditcard.repository

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.features.creditcard.model.CreditCard

interface CreditCardRepository {
    suspend fun getCreditCard(): LiveData<List<CreditCard>>
}