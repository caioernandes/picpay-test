package com.picpay.picpaytest.di

import android.content.Context
import com.picpay.picpaytest.features.payment.viewmodel.PaymentViewModel
import com.picpay.picpaytest.features.users.viewmodel.UsersViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
}