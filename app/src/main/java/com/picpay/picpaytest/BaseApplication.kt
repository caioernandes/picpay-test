package com.picpay.picpaytest

import android.app.Application
import com.picpay.picpaytest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(androidContext = this@BaseApplication)
            modules(appModule)
        }
    }
}