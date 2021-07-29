package com.bektursun.cryptolist.di

import com.bektursun.cryptolist.repository.CryptoListRepository
import com.bektursun.cryptolist.repository.CryptoListRepositoryImpl
import com.bektursun.cryptolist.ui.fragment.cryptolist.CryptoListVM
import com.bektursun.cryptolist.ui.fragment.cryptolist.CryptoListVMImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cryptoListModule = module {
    viewModel<CryptoListVM> { CryptoListVMImpl(get()) }
    single<CryptoListRepository> { CryptoListRepositoryImpl(get(), get()) }
}