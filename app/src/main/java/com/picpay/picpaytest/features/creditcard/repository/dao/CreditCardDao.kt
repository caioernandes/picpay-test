package com.picpay.picpaytest.features.creditcard.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.picpay.picpaytest.features.creditcard.model.CreditCard

@Dao
interface CreditCardDao {

    @Query("SELECT * FROM CreditCard WHERE cardNumber = :cardNumber LIMIT 1")
    fun getCreditCard(cardNumber: String): LiveData<CreditCard>

    @Insert(entity = CreditCard::class)
    fun insertCreditCard(creditCard: CreditCard): Long

    @Update
    fun updateCreditCard(creditCard: CreditCard)
}