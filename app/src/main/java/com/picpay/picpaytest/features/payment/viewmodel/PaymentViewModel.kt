package com.picpay.picpaytest.features.payment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.features.payment.repository.PaymentRepository
import com.picpay.picpaytest.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PaymentViewModel constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel(), CoroutineScope {

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    private var _resultPayment = MutableLiveData<Resource<Unit>>()
    val resultPayment: LiveData<Resource<Unit>> = _resultPayment

    fun postPaymentRequest(paymentRequest: PaymentRequest) {
        launch {
            _resultPayment.value = paymentRepository.postPaymentRequest(paymentRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}