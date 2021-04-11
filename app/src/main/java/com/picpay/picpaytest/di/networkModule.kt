package com.picpay.picpaytest.di

import com.picpay.picpaytest.network.AuthInterceptor
import com.picpay.picpaytest.network.NetworkProvider.provideOkHttpClient
import com.picpay.picpaytest.network.NetworkProvider.providePaymentService
import com.picpay.picpaytest.network.NetworkProvider.provideRetrofit
import com.picpay.picpaytest.network.NetworkProvider.provideUserService
import org.koin.dsl.module

val networkModule = module {
    single { AuthInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    factory { provideUserService(get()) }
    factory { providePaymentService(get()) }
}