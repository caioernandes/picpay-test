package com.picpay.picpaytest.features.payment.repository

import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.utils.Resource

interface PaymentRepository {
    suspend fun postPaymentRequest(paymentRequest: PaymentRequest): Resource<Unit>
}