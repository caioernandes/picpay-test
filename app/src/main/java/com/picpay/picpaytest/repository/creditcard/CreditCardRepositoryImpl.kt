package com.picpay.picpaytest.repository.creditcard

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.db.CreditCardDao
import com.picpay.picpaytest.model.CreditCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val creditCardDao: CreditCardDao
) : CreditCardRepository {

    override suspend fun getCreditCard(): LiveData<List<CreditCard>> =
        withContext(Dispatchers.IO) {
            return@withContext creditCardDao.getCreditCard()
        }
}