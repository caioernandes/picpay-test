package com.picpay.picpaytest.features.creditcard.repository

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.creditcard.model.CreditCardInsert
import com.picpay.picpaytest.features.creditcard.repository.dao.CreditCardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val creditCardDao: CreditCardDao
) : CreditCardRepository {

    override suspend fun getCreditCard(cardNumber: String): LiveData<CreditCard> =
        withContext(Dispatchers.IO) {
            return@withContext creditCardDao.getCreditCard(cardNumber)
        }

    override suspend fun insertCreditCard(creditCard: CreditCardInsert): Long =
        withContext(Dispatchers.IO) {
            creditCardDao.insertCreditCard(creditCard)
        }
}