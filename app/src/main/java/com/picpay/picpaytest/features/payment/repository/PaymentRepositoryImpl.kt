package com.picpay.picpaytest.features.payment.repository

import com.picpay.picpaytest.di.CoroutinesDispatcher
import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.features.payment.repository.service.PaymentRemoteDataSource
import kotlinx.coroutines.withContext

class PaymentRepositoryImpl constructor(
    private val dispatcher: CoroutinesDispatcher,
    private val remoteDataSource: PaymentRemoteDataSource
) : PaymentRepository {

    override suspend fun postPaymentRequest(paymentRequest: PaymentRequest) =
        withContext(dispatcher.IO) {
            remoteDataSource.postPaymentRequest(paymentRequest)
        }
}