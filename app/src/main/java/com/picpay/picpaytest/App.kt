package com.picpay.picpaytest

import android.app.Application
import com.picpay.picpaytest.di.MainInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(MainInjection.appModule)
        }
    }
}