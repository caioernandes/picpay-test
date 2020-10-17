package com.picpay.picpaytest.features.payment.repository

import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.features.payment.repository.service.PaymentRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val remoteDataSource: PaymentRemoteDataSource
) : PaymentRepository {

    override suspend fun postPaymentRequest(paymentRequest: PaymentRequest) =
        withContext(Dispatchers.IO) {
            remoteDataSource.postPaymentRequest(paymentRequest)
        }
}