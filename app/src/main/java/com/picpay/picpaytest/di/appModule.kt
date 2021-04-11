package com.picpay.picpaytest.di

object MainInjection {
    val appModule = listOf(
        viewModelModule,
        networkModule,
        databaseModule,
        remoteDataSourceModule,
        repositoryModule
    )
}
