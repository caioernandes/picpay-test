package com.picpay.picpaytest.di

import com.picpay.picpaytest.features.payment.repository.PaymentRepository
import com.picpay.picpaytest.features.payment.repository.PaymentRepositoryImpl
import com.picpay.picpaytest.features.users.repository.UsersRepository
import com.picpay.picpaytest.features.users.repository.UsersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { CoroutinesDispatcher() }
    factory<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    factory<PaymentRepository> { PaymentRepositoryImpl(get(), get()) }
}