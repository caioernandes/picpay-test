package com.picpay.picpaytest.features.creditcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.creditcard.repository.CreditCardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreditCardViewModel constructor(
    private val repository: CreditCardRepository
): ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    private val viewModelJob = Job()

    private var _creditCardLiveData = MutableLiveData<CreditCard>()
    val creditCardLiveData: LiveData<CreditCard> = _creditCardLiveData

    private var _userInserted = MutableLiveData<Boolean>()
    val userInserted: LiveData<Boolean> = _userInserted

    fun loadCreditCard(cardNumber: String) {
        viewModelScope.launch {
            _creditCardLiveData.value = repository.getCreditCard(cardNumber).value ?: CreditCard()
        }
    }

    fun insertCreditCard(creditCard: CreditCard) {
        viewModelScope.launch {
            val userId = repository.insertCreditCard(creditCard)
            if (userId > 0) {
                _userInserted.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}