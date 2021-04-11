package com.picpay.picpaytest.network

import com.picpay.picpaytest.features.payment.repository.service.PaymentService
import com.picpay.picpaytest.features.users.repository.service.UserService
import com.picpay.picpaytest.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    fun providePaymentService(retrofit: Retrofit): PaymentService {
        return retrofit.create(PaymentService::class.java)
    }
}