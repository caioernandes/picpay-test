package com.picpay.picpaytest.features.creditcard.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.picpay.picpaytest.features.creditcard.model.CreditCard

@Dao
interface CreditCardDao {

    @Query("SELECT * FROM credit_card_table")
    fun getCreditCard(): LiveData<List<CreditCard>>

    @Insert
    fun insertCreditCard(creditCard: CreditCard)

    @Update
    fun updateCreditCard(creditCard: CreditCard)
}