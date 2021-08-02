package com.bektursun.storage.di

import com.bektursun.storage.roomDB.CryptoCurrencyDB
import org.koin.dsl.module

val storageModule = module {
    single { CryptoCurrencyDB.getInstance(get()) }
    single { get<CryptoCurrencyDB>().getCurrencyDao() }
}