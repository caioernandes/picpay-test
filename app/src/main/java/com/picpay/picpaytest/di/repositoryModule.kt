package com.picpay.picpaytest.di

import com.picpay.picpaytest.features.creditcard.repository.CreditCardRepository
import com.picpay.picpaytest.features.creditcard.repository.CreditCardRepositoryImpl
import com.picpay.picpaytest.features.payment.repository.PaymentRepository
import com.picpay.picpaytest.features.payment.repository.PaymentRepositoryImpl
import com.picpay.picpaytest.features.users.repository.UsersRepository
import com.picpay.picpaytest.features.users.repository.UsersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    factory<CreditCardRepository> { CreditCardRepositoryImpl(get()) }
    factory<PaymentRepository> { PaymentRepositoryImpl(get()) }
}