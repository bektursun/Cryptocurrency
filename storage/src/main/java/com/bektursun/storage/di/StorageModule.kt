package com.bektursun.storage.di

import com.bektursun.storage.roodDB.CryptoCurrencyDB
import org.koin.dsl.module

val storageModule = module {
    single { CryptoCurrencyDB.getInstance(get()) }
    single { get<CryptoCurrencyDB>().getCurrencyDao() }
}