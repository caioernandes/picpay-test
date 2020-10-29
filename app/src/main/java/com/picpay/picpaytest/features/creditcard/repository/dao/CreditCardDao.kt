package com.picpay.picpaytest.features.creditcard.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.creditcard.model.CreditCardInsert

@Dao
interface CreditCardDao {

    @Query("SELECT * FROM credit_card_table WHERE cardNumber = :cardNumber LIMIT 1")
    fun getCreditCard(cardNumber: String): LiveData<CreditCard>

    @Insert(entity = CreditCard::class)
    fun insertCreditCard(creditCard: CreditCardInsert): Long

    @Update
    fun updateCreditCard(creditCard: CreditCard)
}