package com.picpay.picpaytest.di

import com.picpay.picpaytest.data.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().usersDao() }
}