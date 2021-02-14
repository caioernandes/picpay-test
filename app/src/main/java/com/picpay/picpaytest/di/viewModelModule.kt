package com.picpay.picpaytest.di

import com.picpay.picpaytest.features.creditcard.viewmodel.CreditCardViewModel
import com.picpay.picpaytest.features.payment.viewmodel.PaymentViewModel
import com.picpay.picpaytest.features.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
    viewModel { CreditCardViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
}