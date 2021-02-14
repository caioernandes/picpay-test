package com.picpay.picpaytest.features.payment.repository.service

import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.utils.BaseDataSource

class PaymentRemoteDataSource constructor(
    private val service: PaymentService
) : BaseDataSource() {

    suspend fun postPaymentRequest(paymentRequest: PaymentRequest) = getResult {
        service.postPaymentRequest(paymentRequest)
    }
}