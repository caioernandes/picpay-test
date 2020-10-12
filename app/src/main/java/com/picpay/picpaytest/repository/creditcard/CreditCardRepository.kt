package com.picpay.picpaytest.repository.creditcard

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.model.CreditCard

interface CreditCardRepository {
    suspend fun getCreditCard(): LiveData<List<CreditCard>>
}