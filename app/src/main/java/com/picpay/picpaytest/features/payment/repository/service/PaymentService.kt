package com.picpay.picpaytest.features.payment.repository.service

import com.picpay.picpaytest.features.payment.model.PaymentRequest
import com.picpay.picpaytest.utils.Constants.POST_PAYMENT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentService {

    @POST(POST_PAYMENT)
    suspend fun postPaymentRequest(@Body paymentRequest: PaymentRequest): Response<Unit>
}