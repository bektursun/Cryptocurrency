package com.bektursun.cryptocurrency.application

import android.app.Application
import com.bektursun.cryptolist.di.cryptoListModule
import com.bektursun.network.di.networkModule
import com.bektursun.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoCurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoCurrencyApp)
            modules(networkModule, storageModule, cryptoListModule)
        }
    }
}