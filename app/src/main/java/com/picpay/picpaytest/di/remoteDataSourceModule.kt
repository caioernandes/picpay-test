package com.picpay.picpaytest.di

import com.picpay.picpaytest.features.payment.repository.service.PaymentRemoteDataSource
import com.picpay.picpaytest.features.users.repository.service.UserRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { UserRemoteDataSource(get()) }
    factory { PaymentRemoteDataSource(get()) }
}