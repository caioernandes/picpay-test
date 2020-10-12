package com.picpay.picpaytest.viewmodel.creditcard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.picpaytest.model.CreditCard
import com.picpay.picpaytest.repository.creditcard.CreditCardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreditCardViewModel @ViewModelInject constructor(
    private val repository: CreditCardRepository
): ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    private val creditCardList = MutableLiveData<List<CreditCard>>()
    val creditCard: LiveData<List<CreditCard>> = creditCardList
    private val viewModelJob = Job()

    fun loadCreditCard() {
        launch {
            val result = repository.getCreditCard()
            creditCardList.value = result.value ?: emptyList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}